package com.example.PrepPilot.AI.controller;
import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> docUpload(
            @RequestParam("file")MultipartFile file,
            @RequestParam("documentType")DocumentType documentType){

        UploadResponse response = documentService.upload(file,documentType);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/documents/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        Resource resource = documentService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + document.getOriginalFileName() + "\"")
                .body(resource);
    }


    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }



}
