package com.example.PrepPilot.AI.dto;


import com.example.PrepPilot.AI.entity.enums.Difficulty;

public record GeneratedQuestion(
        String question,
        String topic,
        Difficulty difficulty
) {
}