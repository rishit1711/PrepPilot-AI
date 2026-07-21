package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.DocumentResponse;
import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    UploadResponse Upload(MultipartFile file, DocumentType documentType);

    Resource downloadFile(Long id);

    void deleteDocument(Long id);

    String extract(MultipartFile file);
}
