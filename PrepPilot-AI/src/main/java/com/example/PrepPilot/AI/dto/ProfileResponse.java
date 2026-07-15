package com.example.PrepPilot.AI.dto;

import java.util.Set;

public record ProfileResponse(
        Long id,
        String fullName,
        String about,
        Integer experience,
        String targetRole,
        String githubUrl,
        String linkedinUrl,
        String portfolioUrl,
        Set<String> skills

) {
}
