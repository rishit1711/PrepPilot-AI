package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import com.example.PrepPilot.AI.dto.AnswerEvaluation;
import com.example.PrepPilot.AI.dto.InterviewContext;
import com.example.PrepPilot.AI.entity.InterviewQuestion;
import com.example.PrepPilot.AI.entity.enums.Difficulty;
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

    public String buildAnswerEvaluationPrompt(
            String question,
            String answer,
            String topic,
            Difficulty difficulty) {

        return """
            You are an AI technical interviewer evaluating a candidate's answer.

            Evaluate the candidate's answer strictly based on the question asked.

            Interview Question:
            %s

            Topic:
            %s

            Question Difficulty:
            %s

            Candidate Answer:
            %s

            Evaluation Rules:
            - Evaluate correctness, relevance, clarity, and technical understanding.
            - Give a score from 0 to 100.
            - Do not give a high score merely because the answer is lengthy.
            - Penalize incorrect technical concepts.
            - If the candidate does not answer the actual question, give an appropriately low score.
            - Consider the difficulty of the question while evaluating.
            - Feedback should clearly explain what was good and what could be improved.
            - Do not evaluate grammar unless it affects the technical meaning.
            - Do not provide a model answer.

            Return ONLY valid JSON.
            Do not include markdown.
            Do not include any explanation outside the JSON.

            Required JSON format:
            {
              "score": 0,
              "feedback": "string"
            }
            """.formatted(
                question,
                topic,
                difficulty,
                answer
        );
    }
    public String buildNextQuestionPrompt(
            InterviewContext context,
            InterviewQuestion question,
            AnswerEvaluation evaluation) {

        return """
            You are an AI technical interviewer conducting an adaptive interview.

            Your task is to generate EXACTLY ONE next interview question.

            Candidate Resume Analysis:
            %s

            Job Description Match Analysis:
            %s

            Interview Blueprint:
            %s

            Current Interview State:
            Question Number: %s
            Current Difficulty: %s

            Previous Question:
            %s

            Previous Question Topic:
            %s

            Previous Question Difficulty:
            %s

            Candidate's Previous Answer:
            %s

            Previous Answer Evaluation:
            Score: %s
            Feedback: %s

            Rules for generating the next question:
            - Generate exactly ONE question.
            - The question must be relevant to the candidate's resume and the target job.
            - Follow the interview blueprint and its topics/priorities.
            - The question must match the CURRENT DIFFICULTY provided above.
            - Use the previous answer and evaluation to make the interview adaptive.
            - If the candidate performed well, challenge the candidate appropriately at the current difficulty.
            - If the candidate performed poorly, ask a question that helps assess the relevant concept at the current difficulty.
            - Do not repeat the previous question.
            - Do not generate multiple questions.
            - Do not provide the answer.
            - Do not provide explanations or hints.
            - Do not mention the evaluation or score in the generated question.
            - Keep the question suitable for a real technical interview.

            Return ONLY valid JSON.
            Do not include markdown.
            Do not include any explanation outside the JSON.

            Required JSON format:
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
                context.getInterviewSession().getCurrentDifficulty(),
                question.getQuestion(),
                question.getTopic(),
                question.getDifficulty(),
                question.getAnswer(),
                evaluation.score(),
                evaluation.feedback()
        );
    }
}