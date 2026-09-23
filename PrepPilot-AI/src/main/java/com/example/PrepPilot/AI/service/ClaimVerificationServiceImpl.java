package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.Orchasterator.promptBuilder.ClaimVerificationPromptBuilder;
import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;
import com.example.PrepPilot.AI.entity.ResumeClaim;
import com.example.PrepPilot.AI.exception.ClaimException;
import com.example.PrepPilot.AI.repository.ResumeClaimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimVerificationServiceImpl implements ClaimVerificationService{
    private  final ResumeClaimRepository resumeClaimRepository;
    private final ClaimVerificationPromptBuilder claimVerificationPromptBuilder;
    private final AIOrchasterator aiOrchasterator;
    @Override
    public String generateQuestion(Long claimId) {
        ResumeClaim resumeClaim = resumeClaimRepository.findById(claimId).orElseThrow(()->new ClaimException("Claim not Present."));
        return "";
    }
}
