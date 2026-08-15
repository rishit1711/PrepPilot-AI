package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JDAnalysisRepository extends JpaRepository<JDMatchAnalysis,Long> {
    JDMatchAnalysis findByResumeIdAndJdId(Long resumeId, Long jdId);
}
