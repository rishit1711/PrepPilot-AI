package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.DocumentType;

import java.time.LocalDateTime;

public record DocumentResponse(
        Long id,
        String originalFileName,
        DocumentType documentType,
        Long fileSize,
        String mimeType,
        LocalDateTime createdAt

) {
}
