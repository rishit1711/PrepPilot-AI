package com.example.PrepPilot.AI.storage;

import com.example.PrepPilot.AI.entity.enums.DocumentType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file, DocumentType documentType);
    Resource load(String storagePath);

    void delete(String storagePath);
}
