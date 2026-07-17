package com.example.PrepPilot.AI.storage;

import com.example.PrepPilot.AI.entity.enums.DocumentType;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class LocalStorageService implements StorageService{
    @Override
    public String store(MultipartFile file, DocumentType documentType) {
        return "";
    }

    @Override
    public Resource load(String storagePath) {
        return null;
    }

    @Override
    public void delete(String storagePath) {

    }
}
