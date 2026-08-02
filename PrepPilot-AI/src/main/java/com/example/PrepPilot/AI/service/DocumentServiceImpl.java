package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.entity.enums.UploadStatus;
import com.example.PrepPilot.AI.exception.IllegalArgumentsException;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.UnauthorizedException;
import com.example.PrepPilot.AI.mapper.DocumentMapper;
import com.example.PrepPilot.AI.RagModule.pdfExtractionService;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService{
    private final StorageService storageService;
    private final UserService userService;
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final pdfExtractionService pdfExtractionService;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;
    private final TokenTextSplitter tokenTextSplitter;

    private static final long MAX_FILE_SIZE=10*1024*1024;

    @Override
    public UploadResponse upload(MultipartFile file, DocumentType documentType) {

        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (file.isEmpty()) {
            throw new ResourceNotFoundException("File Not Found");
        }

        if (documentType == null) {
            throw new IllegalArgumentException("Document Type Required");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentsException("Document Size should not exceed 10MB");
        }

        // Store PDF
        String storedFileName = storageService.store(file, documentType);

        // Save metadata first
        Document documentEntity = Document.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .documentType(documentType)
                .user(user)
                .fileSize(file.getSize())
                .mimeType(file.getContentType())
                .build();

        Document savedDocument = documentRepository.save(documentEntity);

        // Extract text
        String extractedText;
        try {
            extractedText = pdfExtractionService.extractText(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract PDF text", e);
        }


        org.springframework.ai.document.Document aiDocument =
                new org.springframework.ai.document.Document(extractedText);

        // Chunking
        List<org.springframework.ai.document.Document> chunks =
                tokenTextSplitter.split(aiDocument);

        // Add metadata to every chunk
        for (int i = 0; i < chunks.size(); i++) {

            org.springframework.ai.document.Document chunk = chunks.get(i);

            chunk.getMetadata().put("documentId", savedDocument.getId());
            chunk.getMetadata().put("userId", user.getId());
            chunk.getMetadata().put("chunkIndex", i);
            chunk.getMetadata().put("documentType", documentType.name());

        }

        // Store chunks in Qdrant
        vectorStore.add(chunks);

        return new UploadResponse(
                savedDocument.getId(),
                savedDocument.getOriginalFileName(),
                savedDocument.getDocumentType(),
                UploadStatus.UPLOADED
        );
    }

    @Override
    public Resource downloadFile(Long id) {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         Document document = documentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Document not found"));
        if(document.getUser().getId()!=user.getId()){
            throw new UnauthorizedException("You are not Authorized to Access Resources");
        }
         String storedFileName = document.getStoredFileName();

         return storageService.load(storedFileName,document.getDocumentType());

    }

    @Override
    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Document Does not exists"));
        storageService.delete(document.getStoredFileName(),document.getDocumentType());
        documentRepository.deleteById(id);
        return;
    }

    @Override
    public String extract(MultipartFile file) {
        return "";
    }


}
