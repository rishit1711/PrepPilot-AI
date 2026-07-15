package com.example.PrepPilot.AI.dto;

import java.util.HashSet;

public record CreateProfileRequest(

        String fullName,
        Integer experience,
        String about,
        String targetRole,
        String githubUrl,
        String linkedinUrl,
        String portfolioUrl,
        HashSet<String> skills

) {
}
