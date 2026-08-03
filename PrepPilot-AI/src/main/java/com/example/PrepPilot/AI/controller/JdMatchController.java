package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.JDAnalysisRequest;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.service.JDAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JdMatchController {
    private final JDAnalysisService jdAnalysisService;

    @PostMapping("/jd-analyze")
    public ResponseEntity<JDAnalysisResponse> analyze(@RequestBody JDAnalysisRequest request){
        JDAnalysisResponse response = jdAnalysisService.compareJdWithResume(request);
        return ResponseEntity.ok(response);
    }
}
