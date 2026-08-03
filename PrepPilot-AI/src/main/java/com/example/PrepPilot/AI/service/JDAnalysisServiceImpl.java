package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.JDAnalysisRequest;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JDAnalysisServiceImpl implements JDAnalysisService{
    @Override
    public JDAnalysisResponse compareJdWithResume(JDAnalysisRequest request) {
        return null;
    }
}
