package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.StartInterviewResponse;
import com.example.PrepPilot.AI.dto.startInterviewRequest;

public interface InterviewSessionService {
    StartInterviewResponse createSession(startInterviewRequest request);
}
