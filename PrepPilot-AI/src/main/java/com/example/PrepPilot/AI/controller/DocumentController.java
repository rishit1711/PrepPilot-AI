package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.DocumentResponse;
import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/documents/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id){
        Resource documentResponse=documentService.downloadFile(id);
    }



}
