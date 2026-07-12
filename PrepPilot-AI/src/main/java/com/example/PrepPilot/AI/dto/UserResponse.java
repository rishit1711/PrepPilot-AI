package com.example.PrepPilot.AI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}
