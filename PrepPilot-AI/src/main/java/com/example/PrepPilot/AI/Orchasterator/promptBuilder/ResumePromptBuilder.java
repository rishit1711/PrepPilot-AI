package com.example.PrepPilot.AI.Orchasterator.promptBuilder;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResumePromptBuilder {

    public Prompt build(List<Document> chunks) {

        String resumeText = chunks.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        String prompt = """
You are an expert Resume Analyzer and Technical Recruiter.

Analyze the following resume and create a complete candidate profile.

Rules:
- Use ONLY the information present in the resume.
- Do NOT hallucinate or invent information.
- If a field cannot be determined, return an empty string ("") or an empty array ([]).
- ATS score should be an integer between 0 and 100.
- Seniority must be one of:
  ["Fresher", "Intern", "Junior", "Mid-Level", "Senior", "Lead"]
- Suggested roles should be based on the candidate's skills, experience and projects.
- Return ONLY valid JSON.
- Do NOT wrap the response in markdown.
- The JSON keys MUST exactly match the schema below.

Resume:
%s

Return JSON in exactly this format:

{
  "summary": "2-4 sentence professional summary",

  "skills": [
    "Java",
    "Spring Boot"
  ],

  "projects": [
    {
      "title": "Project Name",
      "description": "Project description",
      "technologies": [
        "Java",
        "Spring Boot",
        "Docker"
      ]
    }
  ],

  "experiences": [
    {
      "company": "Company Name",
      "role": "Backend Developer",
      "duration": "Jan 2025 - Present",
      "description": "Worked on REST APIs and Authentication."
    }
  ],

  "education": [
    {
      "degree": "B.Tech Computer Science",
      "institution": "XYZ University",
      "year": "2028"
    }
  ],

  "strengths": [
    "Strong Java backend development",
    "Good understanding of Spring Boot"
  ],

  "weaknesses": [
    "Limited production experience",
    "No cloud deployment experience"
  ],

  "keywords": [
    "Java",
    "Spring Boot",
    "REST API",
    "JWT",
    "PostgreSQL"
  ],

  "atsScore": 82,

  "seniority": "Intern",

  "suggestedRoles": [
    "Java Backend Developer",
    "Spring Boot Developer",
    "Backend Engineer"
  ]
}
""".formatted(resumeText);

        return new Prompt(prompt);
    }
}