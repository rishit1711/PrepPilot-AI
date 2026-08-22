package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.StartInterviewResponse;
import com.example.PrepPilot.AI.dto.startInterviewRequest;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.repository.InterviewSessionRepository;
import com.example.PrepPilot.AI.repository.InterviewTurnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewSessionServiceImpl implements InterviewSessionService{
    private final InterviewSessionRepository interviewSessionRepository;
    private final InterviewTurnRepository interviewTurnRepository;
    @Override
    public StartInterviewResponse createSession(startInterviewRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
