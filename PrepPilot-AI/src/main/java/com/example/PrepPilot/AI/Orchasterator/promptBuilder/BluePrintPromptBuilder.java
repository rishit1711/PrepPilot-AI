package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import org.springframework.stereotype.Component;
import org.springframework.ai.chat.prompt.Prompt;

@Component
public class BluePrintPromptBuilder {

    public Prompt build(
            String jd,
            ResumeAnalysis resumeAnalysis,
            JDMatchAnalysis jdMatchAnalysis) {

        String prompt = """
                You are an Interview Blueprint Generator.

                JOB DESCRIPTION:
                %s

                RESUME ANALYSIS:
                %s

                JD MATCH ANALYSIS:
                %s

                Based on the above context, generate an interview blueprint.

                The blueprint should:
                - Create relevant interview sections.
                - Assign topics to each section.
                - Assign difficulty to each topic.
                - Assign weightage to each topic.
                - Prioritize important JD requirements and candidate weaknesses.
                - Consider relevant resume projects and skills.
                - Do not generate actual interview questions.

                Return only valid JSON.
                """.formatted(
                jd,
                resumeAnalysis,
                jdMatchAnalysis
        );

        return new Prompt(prompt);
    }
}