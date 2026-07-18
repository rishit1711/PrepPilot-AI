package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.entity.enums.UploadStatus;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.mapper.DocumentMapper;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService{
    private final StorageService storageService;
    private final UserService userService;
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    @Override
    public UploadResponse Upload(MultipartFile file, DocumentType documentType) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(file.isEmpty()){
            throw new ResourceNotFoundException("File Not Found");
        }
        String storedFileName = storageService.store(file,documentType);

        User user1 = userService.getUserById(user.getId());

        // storing meta data
        Document document = Document.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .documentType(documentType)
                .user(user)
                .fileSize(file.getSize())
                .build();

        Document saved = documentRepository.save(document);

        UploadResponse response= documentMapper.toUploadResponse(saved);
        return new UploadResponse(response.id(),response.fileName(),response.documentType(), UploadStatus.UPLOADED);

    }
}
