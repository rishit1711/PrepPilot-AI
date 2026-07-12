package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.LoginRequest;
import com.example.PrepPilot.AI.dto.LoginResponse;
import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.UserResponse;
import com.example.PrepPilot.AI.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<UserResponse> signUpUser(@RequestBody RegisterRequest request){
        UserResponse response=authService.registerUser(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        String[] tokens = authService.Login(loginRequest);

        Cookie cookie = new Cookie("refreshToken",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponse(tokens[0]));


    }


}
