package com.example.PrepPilot.AI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;


}
