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

                Your task is to create a structured interview blueprint
                based on the candidate's resume analysis, job description,
                and JD match analysis.

                JOB DESCRIPTION:
                %s

                RESUME ANALYSIS:
                %s

                JD MATCH ANALYSIS:
                %s

                BLUEPRINT REQUIREMENTS:

                1. Create relevant interview sections based on the job description
                   and the candidate's background.

                2. Each section must contain relevant topics that should be tested
                   during the interview.

                3. Assign a difficulty to every topic.
                   Allowed values:
                   EASY, MEDIUM, HARD

                4. Assign a priority to every topic.
                   Allowed values:
                   LOW, MEDIUM, HIGH

                5. Assign a weightage to every section.
                   All section weightages must add up to 100.

                6. Assign a weightage to every topic within a section.
                   Topic weightages within each section must add up to 100.

                7. Assign a sequence number to every section.

                8. Set totalQuestions to the estimated total number of questions
                   for the complete interview.

                9. Give higher priority to important JD requirements,
                   missing skills, weaknesses, and relevant interview focus areas.

                10. Consider relevant skills, experience, and projects from
                    the resume analysis.

                11. Do NOT generate actual interview questions.
                    Generate only the interview blueprint.

                RESPONSE FORMAT:

                Return ONLY valid JSON.
                Do not use markdown.
                Do not add explanations.
                Do not add any fields other than the fields specified below.

                {
                  "totalQuestions": 20,
                  "sections": [
                    {
                      "name": "Core Java",
                      "sequence": 1,
                      "weightage": 30,
                      "topics": [
                        {
                          "name": "Collections",
                          "difficulty": "MEDIUM",
                          "weightage": 40,
                          "priority": "HIGH"
                        }
                      ]
                    }
                  ]
                }
                """.formatted(
                jd,
                resumeAnalysis,
                jdMatchAnalysis
        );

        return new Prompt(prompt);
    }
}