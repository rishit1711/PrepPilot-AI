package com.example.PrepPilot.AI.dto;

public record BluePrintResponse(
        Integer totalQuestions,
        List<BluePrintSectionResponse> sections
) {
}
