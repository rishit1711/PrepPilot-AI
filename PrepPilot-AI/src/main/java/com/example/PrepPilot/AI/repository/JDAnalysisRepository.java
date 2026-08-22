package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JDAnalysisRepository extends JpaRepository<JDMatchAnalysis,Long> {
    JDMatchAnalysis findByResumeIdAndJobDescriptionId(Long resumeId, Long jdId);

    Optional<JDMatchAnalysis> findByUser(User user);
}
