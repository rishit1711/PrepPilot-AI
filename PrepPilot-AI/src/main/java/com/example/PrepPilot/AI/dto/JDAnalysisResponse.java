package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import jakarta.persistence.ManyToOne;

import java.util.List;

public record JDAnalysisResponse(

        Integer overallMatchScore,

        List<String> matchingSkills,

        List<String> missingSkills,

        List<String> strengths,

        List<String> weaknesses,

        List<String> interviewFocusAreas,

        List<String> actionableSuggestions,

        String conciseSummary,
        Long userId,
        Long resumeId,
        Long jobDescriptionId
) {
}