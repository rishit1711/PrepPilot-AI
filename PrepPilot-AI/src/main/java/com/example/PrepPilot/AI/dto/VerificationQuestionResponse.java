package com.example.PrepPilot.AI.dto;

public record VerificationQuestionResponse(
        Long questionId,
        Long claimId,
        String question,
        String claim

) {
}
