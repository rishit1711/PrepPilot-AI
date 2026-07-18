package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService{
    private final StorageService storageService;
    @Override
    public UploadResponse Upload(MultipartFile file, DocumentType documentType, Long userId) {
        if(file.isEmpty()){
            throw new ResourceNotFoundException("File Not Found");
        }
        String storedFileName = storageService.store(file,documentType);
    }
}
