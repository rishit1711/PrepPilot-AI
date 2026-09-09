package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.Difficulty;

public record SubmitAnswerResponse(
        Long sessionId,

        int evaluatedQuestionNumber,
        int score,
        String feedback,

        Long nextQuestionId,
        String nextQuestion,
        String nextTopic,
        Difficulty nextDifficulty,
        int nextQuestionNumber
) {
}