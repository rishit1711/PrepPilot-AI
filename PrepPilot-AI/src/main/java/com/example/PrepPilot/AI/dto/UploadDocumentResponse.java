package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.DocumentType;

public record UploadDocumentResponse(
        Long id,
        String fileName,
        DocumentType documentType,
        String message
) {
}
