package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import com.example.PrepPilot.AI.dto.UpdateProfileRequest;
import com.example.PrepPilot.AI.entity.Profile;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.exception.AlreadyExistException;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.UnauthorizedException;
import com.example.PrepPilot.AI.mapper.ProfileMapper;
import com.example.PrepPilot.AI.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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
                .experience(proileRequest.experience())

                .user(user)
                                                                .
                build();

        Profile savedProfile = profileRepository.save(profile);
        user.setUserProfile(savedProfile);
        return profileMapper.toProfileResponse(savedProfile);




    }

    @Override
    // check lagana ki sirf admin hi sare profile dekh paye
    public List<ProfileResponse> GetAllProfile(User user) {

        List<Profile> profileList = profileRepository.findAll();
        return profileList.stream().map(Profile-> profileMapper.toProfileResponse(Profile)).toList();

    }

    @Override
    public ProfileResponse getMyProfile(User user, Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile does not exist"));

        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public ProfileResponse UpdateProfile(Long id, UpdateProfileRequest profileRequest, User user) {
        Profile profile = profileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Profile does not exist"));
        if(!user.equals(profile.getUser())){
            throw new UnauthorizedException("You are not Authorized to Update Profile");
        }
        ProfileResponse profileResponse = profileMapper.toProfileResponse(profileRequest);
        Profile save = profileMapper.toEntity(profileResponse);
        Profile saved=profileRepository.save(profile);
        return profileMapper.toProfileResponse(saved);
    }
}
