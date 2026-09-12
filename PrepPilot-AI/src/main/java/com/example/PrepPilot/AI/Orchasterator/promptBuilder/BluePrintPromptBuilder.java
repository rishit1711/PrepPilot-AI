package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
public class BluePrintPromptBuilder {

    public Prompt build(
            String jd,
            ResumeAnalysis resumeAnalysis,
            JDMatchAnalysis jdMatchAnalysis) {

        String prompt = """
                You are an Interview Blueprint Generator.

                Your task is to generate a structured interview blueprint
                for a candidate based on:
                1. Job Description
                2. Resume Analysis
                3. JD Match Analysis

                Your output will be consumed directly by a Java backend,
                so the response MUST strictly follow the required JSON structure.

                =========================
                JOB DESCRIPTION
                =========================

                %s

                =========================
                RESUME ANALYSIS
                =========================

                %s

                =========================
                JD MATCH ANALYSIS
                =========================

                %s

                =========================
                BLUEPRINT REQUIREMENTS
                =========================

                1. Generate between 5 and 7 interview sections.

                2. Each section must contain between 3 and 5 relevant topics.

                3. Sections must be based primarily on the job description
                   and candidate profile.

                4. Prioritize:
                   - Important job requirements
                   - Skills required by the JD
                   - Skills missing or weak in the candidate
                   - Skills strongly demonstrated by the candidate
                   - Relevant projects and experience
                   - Important technical interview areas

                5. Each topic must have a difficulty.

                   Allowed values:
                   EASY
                   MEDIUM
                   HARD

                6. Each topic must have a priority.

                   Allowed values:
                   LOW
                   MEDIUM
                   HIGH

                7. Every section must have a sequence number.
                   Sequence must start from 1 and increase by 1.

                8. Every section must have a weightage.

                   Rules:
                   - Section weightages must add up to exactly 100.
                   - Use integer values only.

                9. Every topic must have a weightage.

                   Rules:
                   - Topic weightages within each section must add up to exactly 100.
                   - Use integer values only.

                10. totalQuestions represents the approximate number of
                    questions that should be asked in the complete interview.

                    Allowed range:
                    15 to 25.

                11. Do NOT generate actual interview questions.

                12. Generate only the blueprint:
                    sections, topics, difficulty, priority,
                    weightages, sequence, and totalQuestions.

                13. Do not create unnecessary or duplicate topics.

                14. Keep section names and topic names concise.

                =========================
                IMPORTANT OUTPUT RULES
                =========================

                Return ONLY valid JSON.

                DO NOT:
                - Use markdown
                - Use ```json
                - Add explanations
                - Add comments
                - Add introductory text
                - Add concluding text
                - Add fields not defined in the schema
                - Return partial JSON

                The JSON response MUST be complete and syntactically valid.

                Before finishing the response, make sure:
                - Every topic object is closed.
                - Every topics array is closed.
                - Every section object is closed.
                - Every sections array is closed.
                - The root JSON object is closed.

                NEVER stop generation while inside an object or array.

                =========================
                REQUIRED JSON STRUCTURE
                =========================

                {
                  "totalQuestions": 20,
                  "sections": [
                    {
                      "name": "Core Java and OOP",
                      "sequence": 1,
                      "weightage": 20,
                      "topics": [
                        {
                          "name": "OOP Concepts",
                          "difficulty": "MEDIUM",
                          "weightage": 40,
                          "priority": "HIGH"
                        },
                        {
                          "name": "Collections",
                          "difficulty": "MEDIUM",
                          "weightage": 35,
                          "priority": "HIGH"
                        },
                        {
                          "name": "Multithreading",
                          "difficulty": "HARD",
                          "weightage": 25,
                          "priority": "MEDIUM"
                        }
                      ]
                    }
                  ]
                }

                IMPORTANT:
                The example above is only a structural example.
                Generate the actual sections and topics based on the
                provided JD, Resume Analysis, and JD Match Analysis.
                """.formatted(
                jd,
                resumeAnalysis,
                jdMatchAnalysis
        );

        return new Prompt(prompt);
    }
}