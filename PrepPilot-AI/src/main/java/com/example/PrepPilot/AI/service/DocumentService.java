package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    UploadResponse Upload(MultipartFile file, DocumentType documentType);

}
