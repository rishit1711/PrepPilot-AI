package com.example.PrepPilot.AI.dto;

public record SubmitAnswerRequest(
        Long questionId,
        String answer
) {
}
