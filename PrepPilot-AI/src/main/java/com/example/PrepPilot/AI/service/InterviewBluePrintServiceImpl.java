package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.exception.DocumentNotFoundException;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.UnauthorizedException;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.repository.JDAnalysisRepository;
import com.example.PrepPilot.AI.repository.ResumeAnalysisRepository;
import com.example.PrepPilot.AI.repository.UserRepository;
import com.example.PrepPilot.AI.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewBluePrintServiceImpl implements InterviewBluePrintService {
    private final DocumentRepository documentRepository;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final JDAnalysisRepository jdAnalysisRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AIOrchasterator aiOrchasterator;
    @Override
    public BluePrintResponse GenerateBluePrint(BluePrintRequest request) {
        Long userId = jwtService.getUserId();
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not Found."));
        // documtent >> JD
        Document document = documentRepository.findById(request.jd_id()).orElseThrow(()->new DocumentNotFoundException("Job Description not found."));
        // documrnt1 ->> Resume
        Document document1 = documentRepository.findById(request.resume_id()).orElseThrow(()->new DocumentNotFoundException("Resume not found."));
        if(document.getUser().getId()!=userId){
            throw new UnauthorizedException("Not Authorized");
        }
        if(document1.getUser().getId()!=userId){
            throw new UnauthorizedException("Not Authorized");
        }
        ResumeAnalysis resumeAnalysis = resumeAnalysisRepository.findByResumeId(request.resume_id());
        JDMatchAnalysis jdMatchAnalysis = jdAnalysisRepository.findByResumeIdAndJdId(request.resume_id(),request.jd_id());

        BluePrintResponse response = aiOrchasterator.generateBluePrint(document,resumeAnalysis,jdMatchAnalysis);
    }
}
