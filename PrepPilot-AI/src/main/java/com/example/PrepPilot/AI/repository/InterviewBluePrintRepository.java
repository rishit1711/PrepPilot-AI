package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.InterviewBluePrint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewBluePrintRepository extends JpaRepository<InterviewBluePrint,Long> {

    Optional<InterviewBluePrint > findByIdAndUserId(Long userId, Long id);
}
