package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;

public interface ClaimVerificationService {
    String generateQuestion(Long claimId);
}
