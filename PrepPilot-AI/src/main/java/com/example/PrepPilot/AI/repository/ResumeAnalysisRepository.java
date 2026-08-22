package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import com.example.PrepPilot.AI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis,Long> {
    ResumeAnalysis findByResumeId(Long resumeId);
    Optional<ResumeAnalysis> findByUser(User user);
}
