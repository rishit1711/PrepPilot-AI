package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.enums.Role;

public record UserResponse(

        Long id,
        String name,
        String email,
        Role role
) {
}
