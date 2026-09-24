package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.ClaimAnswerEvaluation;
import com.example.PrepPilot.AI.dto.ClaimAnswerRequest;
import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;

public interface ClaimVerificationService {
    VerificationQuestionResponse generateQuestion(Long claimId);
    ClaimAnswerEvaluation evaluateAnswer(ClaimAnswerRequest claimAnswerRequest);
}
