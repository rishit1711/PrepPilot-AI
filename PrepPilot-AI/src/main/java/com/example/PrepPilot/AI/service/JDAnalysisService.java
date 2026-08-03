package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.JDAnalysisRequest;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;

public interface JDAnalysisService {
    JDAnalysisResponse compareJdWithResume(JDAnalysisRequest request);
}
