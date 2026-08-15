package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.repository.JDAnalysisRepository;
import com.example.PrepPilot.AI.repository.ResumeAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewBluePrintServiceImpl implements InterviewBluePrintService {
    private final DocumentRepository documentRepository;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final JDAnalysisRepository jdAnalysisRepository;
    @Override
    public BluePrintResponse GenerateBluePrint(BluePrintRequest request) {

        ResumeAnalysis resumeAnalysis = resumeAnalysisRepository.findByResumeId(request.resume_id());
        JDMatchAnalysis jdMatchAnalysis = jdAnalysisRepository.findByResumeIdAndJdId(request.resume_id(),request.jd_id());
    }
}
