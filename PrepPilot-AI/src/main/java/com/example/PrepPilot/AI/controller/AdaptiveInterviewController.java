package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.SubmitAnswerRequest;
import com.example.PrepPilot.AI.service.InterviewSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class AdaptiveInterviewController {
    private final InterviewSessionService interviewSessionService;

    @PostMapping("/{sessionId}/answer")
    public SubmitAnswerResponse submitAnswer(
            @PathVariable Long sessionId,
            @RequestBody SubmitAnswerRequest request) {

        return interviewSessionService.submitAnswer(sessionId, request);
    }

}
