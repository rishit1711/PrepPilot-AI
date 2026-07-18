package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.DocumentType;

public record UploadResponse(
        Long id,
        String fileName,
        DocumentType documentType,
        String message
) {
}
