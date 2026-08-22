package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.service.InterviewSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class InterviewSessionController {
    private final InterviewSessionService interviewSessionService;


}
