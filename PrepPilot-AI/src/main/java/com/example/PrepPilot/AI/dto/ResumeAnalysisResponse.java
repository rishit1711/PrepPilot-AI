package com.example.PrepPilot.AI.dto;

import java.util.List;

public record ResumeAnalysisResponse(
        String summary,

        List<String> skills ,

        List<ProjectDto> projects,

        List<ExperienceDto> experiences,

        List<EducationDto> education
) {
}
