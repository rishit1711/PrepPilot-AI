package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis,Long> {
    ResumeAnalysis findByResumeId(Long resumeId);
}
