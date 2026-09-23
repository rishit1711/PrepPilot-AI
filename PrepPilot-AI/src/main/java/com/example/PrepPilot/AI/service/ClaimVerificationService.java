package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;

public interface ClaimVerificationService {
    VerificationQuestionResponse generateQuestion(Long claimId);
}
