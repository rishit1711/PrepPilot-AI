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
    public String buildNextClaimVerificationQuestionPrompt(
            String claim,
            String previousQuestion,
            String previousAnswer,
            String evaluation,
            String resumeContext
    ) {

        return """
            You are an adaptive technical interviewer verifying a
            candidate's resume claim.

            RESUME CLAIM:
            %s

            RESUME CONTEXT:
            %s

            PREVIOUS QUESTION:
            %s

            CANDIDATE'S ANSWER:
            %s

            EVALUATION OF THE ANSWER:
            %s

            Your task is to generate the NEXT question that should be asked
            to verify the candidate's claimed skill.

            The next question must be based on the evidence obtained from
            the candidate's previous answer.

            Follow these rules:

            1. Identify what the candidate demonstrated correctly.
            2. Identify important knowledge gaps, misconceptions, or
               unverified areas from the evaluation.
            3. Generate a question that specifically probes one of those
               missing or weak areas.
            4. Do NOT repeat the previous question.
            5. Do NOT ask an unrelated question.
            6. Do NOT simply increase difficulty without a reason.
            7. If the candidate demonstrated strong understanding,
               probe a deeper practical or scenario-based aspect of
               the same skill.
            8. If the candidate showed weak understanding, ask a
               foundational follow-up question that helps determine
               whether the weakness is genuine.
            9. Do not reveal the answer or guide the candidate toward it.
            10. The question must help produce additional evidence
                about the resume claim.

            Return only the next interview question.
            """.formatted(
                claim,
                resumeContext,
                previousQuestion,
                previousAnswer,
                evaluation
        );
    }

}
