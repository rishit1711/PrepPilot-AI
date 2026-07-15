package com.example.PrepPilot.AI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


public record UserResponse(
         Long id,
         String name,
         String email
) {

}
