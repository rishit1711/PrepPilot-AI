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
        - Calculate an overall matchScore between 0 and 100.
        - Identify matching technical and soft skills.
        - Identify missing required skills.
        - Identify candidate strengths.
        - Identify candidate weaknesses.
        - Suggest interview focus areas.
        - Provide actionable suggestions for improving the resume.
        - Provide a concise summary.

        Rules:
        - Use ONLY the provided Resume and Job Description.
        - Do NOT hallucinate or assume information that is not present.
        - If information is unavailable, return an empty array or empty string.
        - Return ONLY valid JSON.
        - Do NOT wrap the response inside ```json``` blocks.
        - Do NOT include explanations before or after the JSON.
        - Use EXACTLY the following JSON schema with the same field names.

        {
          "overallMatchScore": 0,
          "matchingSkills": [],
          "missingSkills": [],
          "strengths": [],
          "weaknesses": [],
          "interviewFocusAreas": [],
          "actionableSuggestions": [],
          "conciseSummary": ""
        }
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