package com.example.PrepPilot.AI.dto;

public record VerificationQuestionResponse(
        int questionId,
        Long claimId,
        String question,
        String claim

) {
}
