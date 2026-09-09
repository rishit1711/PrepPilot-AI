package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.StartInterviewResponse;
import com.example.PrepPilot.AI.dto.SubmitAnswerRequest;
import com.example.PrepPilot.AI.dto.SubmitAnswerResponse;
import com.example.PrepPilot.AI.dto.startInterviewRequest;


public interface InterviewSessionService {
    StartInterviewResponse createSession(startInterviewRequest request);

    SubmitAnswerResponse submitAnswer(Long sessionId, SubmitAnswerRequest request);
}
