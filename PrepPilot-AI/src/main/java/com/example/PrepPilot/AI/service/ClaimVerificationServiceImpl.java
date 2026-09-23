package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.Orchasterator.promptBuilder.ClaimVerificationPromptBuilder;
import com.example.PrepPilot.AI.entity.ResumeClaim;
import com.example.PrepPilot.AI.exception.ClaimException;
import com.example.PrepPilot.AI.repository.ResumeClaimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClaimVerificationServiceImpl
        implements ClaimVerificationService {

    private final ResumeClaimRepository resumeClaimRepository;
    private final ClaimVerificationPromptBuilder claimVerificationPromptBuilder;
    private final AIOrchasterator aiOrchasterator;

    @Override
    public String generateQuestion(Long claimId) {

        // 1. Fetch claim
        ResumeClaim resumeClaim = resumeClaimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimException("Resume claim not found with id: " + claimId)
                );

        // 2. Get actual claim
        String claim = resumeClaim.getClaim();

        log.info("Generating verification question for claim: {}", claim);

        // 3. Build prompt
        String prompt = claimVerificationPromptBuilder
                .buildClaimVerificationQuestionPrompt(claim);

        // 4. Ask AI
        String question = aiOrchasterator.generateText(prompt);

        // 5. Return generated question
        return question;
    }
}