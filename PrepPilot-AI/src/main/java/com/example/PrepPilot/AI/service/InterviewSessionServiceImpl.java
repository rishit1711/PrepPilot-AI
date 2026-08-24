package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.InterviewContext;
import com.example.PrepPilot.AI.dto.StartInterviewResponse;
import com.example.PrepPilot.AI.dto.startInterviewRequest;
import com.example.PrepPilot.AI.entity.*;
import com.example.PrepPilot.AI.entity.enums.Difficulty;
import com.example.PrepPilot.AI.entity.enums.InterviewStatus;
import com.example.PrepPilot.AI.exception.DocumentNotFoundException;
import com.example.PrepPilot.AI.repository.*;
import com.example.PrepPilot.AI.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewSessionServiceImpl implements InterviewSessionService{
    private final InterviewSessionRepository interviewSessionRepository;
    private final InterviewTurnRepository interviewTurnRepository;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final AIOrchasterator aiOrchasterator;
    private final JDAnalysisRepository jdAnalysisRepository;
    private final InterviewBluePrintRepository interviewBluePrintRepository;

    @Override
    public StartInterviewResponse createSession(startInterviewRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResumeAnalysis resumeAnalysis = resumeAnalysisRepository.findByUser(user).orElseThrow(()->new DocumentNotFoundException("Resume Analysis not Found"));
        JDMatchAnalysis jdMatchAnalysis= jdAnalysisRepository.findByUser(user).orElseThrow(()->new DocumentNotFoundException("JD Analysis not found"));
        InterviewBluePrint bluePrint = interviewBluePrintRepository.findByIdAndUserId(request.bluePrintId(),user.getId());
        InterviewSession session = InterviewSession.builder()
                .user(user)
                        .interviewStatus(InterviewStatus.IN_PROGRESS)
                                .currentDifficulty(Difficulty.EASY)
                                        .questionNumber(1).build();

        interviewSessionRepository.save(session);


        InterviewContext context = InterviewContext.builder()
                        .interviewBluePrint(bluePrint)
                                .resumeAnalysis(resumeAnalysis)
                                        .jdMatchAnalysis(jdMatchAnalysis)
                                                .interviewSession(session)
                                                        .build();

        StartInterviewResponse response = aiOrchasterator.generateQuestion(context);

        return response;


    }
}
