package com.example.PrepPilot.AI.service;
import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.Orchasterator.promptBuilder.ClaimVerificationPromptBuilder;
import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;
import com.example.PrepPilot.AI.entity.ClaimQuestion;
import com.example.PrepPilot.AI.entity.ResumeClaim;
import com.example.PrepPilot.AI.exception.ClaimException;
import com.example.PrepPilot.AI.repository.ClaimQuestionRepository;
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
    private final ClaimQuestionRepository claimQuestionRepository;

    @Override
    public VerificationQuestionResponse generateQuestion(Long claimId) {

        ResumeClaim resumeClaim = resumeClaimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimException("Resume claim not found with id: " + claimId)
                );

        String claim = resumeClaim.getClaim();

        log.info("Generating verification question for claim: {}", claim);

        String prompt = claimVerificationPromptBuilder
                .buildClaimVerificationQuestionPrompt(claim);

        VerificationQuestionResponse response =
                aiOrchasterator.generateText(prompt);

        ClaimQuestion claimQuestion = ClaimQuestion.builder()
                .question(response.question())
                .resumeClaim(resumeClaim)
                .build();

        ClaimQuestion savedQuestion =
                claimQuestionRepository.save(claimQuestion);

        return new VerificationQuestionResponse(
                response.claimId(),
                savedQuestion.getId(),
                response.claim(),
                response.question()
        );
    }
}