package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.Difficulty;
import com.example.PrepPilot.AI.entity.enums.Priority;

public record BluePrintTopicResponse(
        String name,
        Difficulty difficulty,
        Integer weightage,
        Priority priority
) {
}
