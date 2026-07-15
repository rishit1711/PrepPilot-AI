package com.example.PrepPilot.AI.dto;

import java.util.HashSet;

public record UpdateProfileRequest(
        String about,
        String githubUrl,
        String linkedinUrl,
        String portfolioUrl,
        HashSet<String> skills

) {
}
