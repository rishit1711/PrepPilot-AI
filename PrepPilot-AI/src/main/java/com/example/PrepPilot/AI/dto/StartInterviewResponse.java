package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.Difficulty;

public record StartInterviewResponse(
        Long sessionId,
        Long questionId,
        String question,
        String topic,
        Difficulty difficulty,
        int questionNumber
) {
}
