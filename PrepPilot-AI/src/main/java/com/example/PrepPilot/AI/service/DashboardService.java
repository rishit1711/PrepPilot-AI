package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.DashboardResponse;
import com.example.PrepPilot.AI.entity.Profile;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProfileRepository profileRepository;

    public DashboardResponse getDashboard(User user) {

        Profile profile = profileRepository.findByUser(user);

        int completed = 0;
        int total = 7;

        if (profile.getFullName() != null && !profile.getFullName().isBlank()) completed++;
        if (profile.getAbout() != null && !profile.getAbout().isBlank()) completed++;
        if (profile.getExperience() != null) completed++;
        if (profile.getTargetRole() != null && !profile.getTargetRole().isBlank()) completed++;
        if (profile.getGithubUrl() != null && !profile.getGithubUrl().isBlank()) completed++;
        if (profile.getLinkedinUrl() != null && !profile.getLinkedinUrl().isBlank()) completed++;
        if (profile.getSkills() != null && !profile.getSkills().isEmpty()) completed++;

        int profileCompletion = (completed * 100) / total;

        return new DashboardResponse(
                profileCompletion,
                false,   // Resume uploaded
                false,   // JD uploaded
                0,       // Total Documents
                0,       // Interviews
                0.0      // Average Score
        );
    }
}