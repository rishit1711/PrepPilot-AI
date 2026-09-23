package com.example.PrepPilot.AI.dto;

import java.util.List;

public record ClaimAnswerEvaluation(
        double confidenceScore,
        boolean sufficientEvidence,
        String evidence,
        List<String> knowledgeGaps,
        List<String> strengths
) {
}