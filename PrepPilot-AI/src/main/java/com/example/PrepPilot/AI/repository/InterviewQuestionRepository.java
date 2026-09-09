package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion,Long> {
    Optional<Object> findByIdAndSessionId(Long aLong, Long sessionId);
}
