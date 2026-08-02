package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeRequest;

public interface ResumeAnalysisService {
    ResumeAnalysisResponse analyzeResume(ResumeRequest resumeRequest);
}
