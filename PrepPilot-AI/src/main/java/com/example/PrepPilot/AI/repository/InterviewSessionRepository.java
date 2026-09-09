package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession,Long> {
    Optional<Object> findByIdAndUserId(Long sessionId, Long id);
}
