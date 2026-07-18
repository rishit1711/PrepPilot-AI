package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.entity.enums.UploadStatus;

public record UploadResponse(
        Long id,
        String fileName,
        DocumentType documentType,
        UploadStatus uploadStatus
) {
}
