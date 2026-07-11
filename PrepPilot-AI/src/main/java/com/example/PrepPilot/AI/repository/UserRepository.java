package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
