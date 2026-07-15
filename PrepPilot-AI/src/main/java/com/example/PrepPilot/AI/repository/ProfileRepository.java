package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.Profile;
import com.example.PrepPilot.AI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    boolean existsByUser(User user);
}
