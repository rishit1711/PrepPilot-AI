package com.example.PrepPilot.AI.Orchasterator.promptBuilder;


import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDPromptBuilder {

    private static final String SYSTEM_PROMPT = """
            You are an expert ATS (Applicant Tracking System) and Technical Hiring Assistant.

            Your task is to analyze a candidate's resume against a given job description.

            Evaluation Guidelines:
            - Calculate an overall match score (0-100).
            - Identify matching skills.
            - Identify missing skills.
            - Identify strengths.
            - Identify weaknesses.
            - Determine interview focus areas.
            - Generate actionable suggestions.
            - Provide a concise summary.

            Rules:
            - Use ONLY the provided context.
            - Do NOT hallucinate.
            - Return ONLY valid JSON.
            """;

    public Prompt build(List<Document> resumeChunks,
                        List<Document> jdChunks) {

        String resumeContext = resumeChunks.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n" + b);

        String jdContext = jdChunks.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n" + b);

        String userPrompt = """
                Resume Context:
                %s

                ----------------------------------------------------

                Job Description Context:
                %s

                Analyze the resume against the job description and return the response in the required JSON format.
                """.formatted(resumeContext, jdContext);

        return new Prompt(SYSTEM_PROMPT + "\n\n" + userPrompt);
    }
}