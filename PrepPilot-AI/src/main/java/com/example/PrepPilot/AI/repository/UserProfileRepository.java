package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
