package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

    @Component
    public class ResumePromptBuilder {

        private static final String TEMPLATE = """
            You are an expert Resume Analyzer.

            Analyze the resume carefully.

            Rules:
            - Use ONLY the provided resume.
            - Do NOT hallucinate.
            - Return ONLY valid JSON.
            - Do not wrap JSON inside markdown.

            Resume:
            {resume}

            Expected JSON Schema:

            {
              "summary": "",
              "skills": [],
              "projects": [],
              "experience": [],
              "education": []
            }
            """;

        public Prompt build(List<Document> chunks) {

            String resumeText = chunks.stream()
                    .map(Document::getText)
                    .collect(Collectors.joining("\n\n"));

            PromptTemplate promptTemplate = new PromptTemplate(TEMPLATE);

            return promptTemplate.create(
                    Map.of("resume", resumeText)
            );
        }

    }

