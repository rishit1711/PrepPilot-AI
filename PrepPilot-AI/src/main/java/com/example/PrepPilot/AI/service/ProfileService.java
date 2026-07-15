package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import com.example.PrepPilot.AI.entity.User;

import java.util.List;

public interface ProfileService {
    ProfileResponse GenerateProfile(CreateProfileRequest proileRequest , User user);

    List<ProfileResponse> GetAllProfile(User user);
}
