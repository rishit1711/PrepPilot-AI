package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.entity.*;
import com.example.PrepPilot.AI.exception.DocumentNotFoundException;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.UnauthorizedException;
import com.example.PrepPilot.AI.mapper.BluePrintMapper;
import com.example.PrepPilot.AI.repository.*;
import com.example.PrepPilot.AI.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class InterviewBluePrintServiceImpl implements InterviewBluePrintService {
    private final DocumentRepository documentRepository;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final JDAnalysisRepository jdAnalysisRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AIOrchasterator aiOrchasterator;
    private final InterviewBluePrintRepository interviewBluePrintRepository;
    private final BluePrintMapper bluePrintMapper;

    @Override

    public BluePrintResponse GenerateBluePrint(BluePrintRequest request) {

        Long userId = jwtService.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not Found.")
                );

        // JD
        Document jd = documentRepository.findById(request.jd_id())
                .orElseThrow(() ->
                        new DocumentNotFoundException("Job Description not found.")
                );

        // Resume
        Document resume = documentRepository.findById(request.resume_id())
                .orElseThrow(() ->
                        new DocumentNotFoundException("Resume not found.")
                );

        // Authorization
        if (!jd.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not Authorized");
        }

        if (!resume.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not Authorized");
        }

        // Existing AI analysis
        ResumeAnalysis resumeAnalysis =
                resumeAnalysisRepository.findByResumeId(request.resume_id());

        JDMatchAnalysis jdMatchAnalysis =
                jdAnalysisRepository.findByResumeIdAndJobDescriptionId(
                        request.resume_id(),
                        request.jd_id()
                );

        // Generate blueprint using AI
        BluePrintResponse response =
                aiOrchasterator.generateBluePrint(
                        jd,
                        resumeAnalysis,
                        jdMatchAnalysis
                );


        InterviewBluePrint bluePrint =
                bluePrintMapper.toBluePrint(response);


        bluePrint.setUserId(userId);
        bluePrint.setResumeId(request.resume_id());
        bluePrint.setJd_id(request.jd_id());
        bluePrint.setCreatedAt(Instant.now());

        // Save
        System.out.println("USER = " + bluePrint.getUserId());
        System.out.println("RESUME = " + bluePrint.getResumeId());
        System.out.println("JD = " + bluePrint.getJd_id());
        System.out.println("QUESTIONS = " + bluePrint.getTotalQuestions());
        interviewBluePrintRepository.save(bluePrint);

        return response;
    }
}