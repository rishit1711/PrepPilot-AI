package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import com.example.PrepPilot.AI.entity.Profile;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.exception.AlreadyExistException;
import com.example.PrepPilot.AI.mapper.ProfileMapper;
import com.example.PrepPilot.AI.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements  ProfileService{
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponse GenerateProfile(CreateProfileRequest proileRequest,User user) {

        if(profileRepository.existsByUser(user)){
            throw new AlreadyExistException("Profile Already Exists!");
        }

        Profile profile = Profile.builder()
                        .about(proileRequest.about())
                .createdAt(Instant.now())
                        .fullName(proileRequest.fullName())
                                .githubUrl(proileRequest.githubUrl())
                                        .linkedinUrl(proileRequest.linkedinUrl())
                                                .portfolioUrl(proileRequest.portfolioUrl())
                                                        .targetRole(proileRequest.targetRole())
                .skills(proileRequest.skills())
                .user(user)
                                                                .
                build();

        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toProfileResponse(savedProfile);




    }
}
