package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import org.springframework.stereotype.Component;

@Component
public class ClaimVerificationPromptBuilder {
    public String buildClaimVerificationQuestionPrompt(
            String claim,
            String resumeContext,
            String jobDescriptionContext
    ) {

        return """
            You are an AI technical interviewer responsible for verifying a
            candidate's resume claim.

            The candidate has claimed the following skill:

            CLAIM:
            %s

            RESUME CONTEXT:
            %s

            JOB DESCRIPTION CONTEXT:
            %s

            Your task is to generate ONE technical interview question that
            can provide meaningful evidence of whether the candidate
            genuinely understands this skill.

            Requirements:
            - The question must directly relate to the claimed skill.
            - Test practical understanding rather than simple memorization.
            - Prefer scenario-based or reasoning-based questions.
            - Do not ask a question that can be answered with a single word.
            - Do not reveal the expected answer.
            - Do not mention that the question is being used for verification.
            - The question should be appropriate for the candidate's
              apparent experience level.
            
            Return only the question.
            """.formatted(
                claim,
                resumeContext,
                jobDescriptionContext
        );
    }

}
