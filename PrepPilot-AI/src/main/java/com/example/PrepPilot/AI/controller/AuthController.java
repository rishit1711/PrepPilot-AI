package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.RegisterResponse;
import com.example.PrepPilot.AI.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signUpUser(@RequestBody RegisterRequest request){
        RegisterResponse response=authService.registerUser(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


}
