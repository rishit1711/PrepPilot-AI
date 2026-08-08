package com.example.PrepPilot.AI.dto;

import java.util.List;

public record ProjectDto(
        String title,
        String description,
        List<String> technologies


) {
}