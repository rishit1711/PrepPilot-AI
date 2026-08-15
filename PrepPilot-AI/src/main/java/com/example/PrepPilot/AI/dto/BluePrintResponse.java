package com.example.PrepPilot.AI.dto;

import java.util.List;

public record BluePrintResponse(
        Integer totalQuestions,
        List<BluePrintSectionResponse> sections
) {
}
