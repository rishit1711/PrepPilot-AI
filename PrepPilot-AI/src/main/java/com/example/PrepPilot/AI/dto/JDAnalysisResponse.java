package com.example.PrepPilot.AI.dto;

import java.util.List;

public record JDAnalysisResponse(

        Integer overallMatchScore,

        List<String> matchingSkills,

        List<String> missingSkills,

        List<String> strengths,

        List<String> weaknesses,

        List<String> interviewFocusAreas,

        List<String> actionableSuggestions,

        String conciseSummary

) {
}