package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.DocumentResponse;
import com.example.PrepPilot.AI.dto.UploadResponse;
import com.example.PrepPilot.AI.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    UploadResponse toUploadResponse(Document document);

    DocumentResponse toDocumentResponse(Document document);
}
