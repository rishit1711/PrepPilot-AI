package com.example.PrepPilot.AI.dto;

public record DashboardResponse(
        Integer profileCompletion,

        Boolean resumeUploaded,

        Boolean jobDescriptionUploaded,

        Integer totalDocuments,

        Integer totalInterviews,

        Double averageScore
) {
}
