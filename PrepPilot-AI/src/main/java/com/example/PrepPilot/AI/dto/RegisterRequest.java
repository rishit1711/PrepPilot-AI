package com.example.PrepPilot.AI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public record RegisterRequest(
        String name,
         String email,
         String password

) {




}
