package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import com.example.PrepPilot.AI.dto.InterviewContext;
import org.springframework.stereotype.Component;

@Component
public class AdaptiveInterviewPromptBuilder {

    public String buildFirstQuestionPrompt(InterviewContext context) {

        return """
                You are an AI interviewer conducting a technical interview.

                Your task is to generate the FIRST interview question for the candidate.

                =========================
                CANDIDATE RESUME ANALYSIS
                =========================
                %s

                =========================
                JOB DESCRIPTION MATCH
                =========================
                %s

                =========================
                INTERVIEW BLUEPRINT
                =========================
                %s

                =========================
                CURRENT INTERVIEW STATE
                =========================
                Question Number: %d
                Current Difficulty: %s

                =========================
                INSTRUCTIONS
                =========================

                1. Generate exactly ONE interview question.
                2. The question must be relevant to the candidate's resume.
                3. The question must be relevant to the job requirements.
                4. Follow the interview blueprint when selecting the topic.
                5. The question must match the current difficulty level.
                6. Do not ask multiple questions in one response.
                7. Do not provide the answer.
                8. Do not provide an explanation or feedback.
                9. Do not repeat information unnecessarily.
                10. Return only the requested JSON structure.

                =========================
                RESPONSE FORMAT
                =========================

                {
                  "question": "string",
                  "topic": "string",
                  "difficulty": "EASY | MEDIUM | HARD"
                }
                """.formatted(
                context.getResumeAnalysis(),
                context.getJdMatchAnalysis(),
                context.getInterviewBluePrint(),
                context.getInterviewSession().getQuestionNumber(),
                context.getInterviewSession().getCurrentDifficulty()
        );
    }
}