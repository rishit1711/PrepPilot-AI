package com.example.PrepPilot.AI.dto;

import java.util.List;

public record BluePrintSectionResponse(
        String name,
        Integer sequence,
        Integer weightage,
        List<BluePrintTopicResponse> topics
) {
}
