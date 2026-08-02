package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeRequest;
import com.example.PrepPilot.AI.service.ResumeAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeAnalysisService resumeAnalysisService;

    @PostMapping("/analyze/resume")

    public ResponseEntity<ResumeAnalysisResponse> anaylyze(@RequestBody ResumeRequest resumeRequest){
        ResumeAnalysisResponse response = resumeAnalysisService.analyzeResume(resumeRequest);
        return ResponseEntity.ok(response);
    }


}
