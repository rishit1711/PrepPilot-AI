package com.example.PrepPilot.AI.dto;

public record VerificationQuestionResponse(
        Long claimId,
        String question,
        String claim

) {
}
