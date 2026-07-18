package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> docUpload(
            @RequestParam("file")MultipartFile file,
            @RequestParam("documentType")DocumentType documentType){

        UploadResponse response = documentService.Upload(file,documentType);
        return ResponseEntity.ok(response);
    }

}
