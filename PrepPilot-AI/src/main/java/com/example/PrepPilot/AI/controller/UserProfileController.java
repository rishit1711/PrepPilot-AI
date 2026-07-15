package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class UserProfileController {

    private final ProfileService profileService;


    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody CreateProfileRequest proileRequest){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(profileService.GenerateProfile(proileRequest,user));

    }



}
