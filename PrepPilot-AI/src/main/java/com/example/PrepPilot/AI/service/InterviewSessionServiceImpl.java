package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.*;
import com.example.PrepPilot.AI.entity.*;
import com.example.PrepPilot.AI.entity.enums.Difficulty;
import com.example.PrepPilot.AI.entity.enums.InterviewStatus;
import com.example.PrepPilot.AI.exception.DocumentNotFoundException;
import com.example.PrepPilot.AI.exception.InvalidBluePrint;
import com.example.PrepPilot.AI.mapper.InterviewResponseMapper;
import com.example.PrepPilot.AI.repository.*;
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
    private  final InterviewResponseMapper interviewResponseMapper;
    private final InterviewQuestionRepository interviewQuestionRepository;

    private Difficulty decideDifficulty(
            Difficulty currentDifficulty,
            int score) {

        if (score >= 80) {

            if (currentDifficulty == Difficulty.EASY) {
                return Difficulty.MEDIUM;
            }

            if (currentDifficulty == Difficulty.MEDIUM) {
                return Difficulty.HARD;
            }

            return Difficulty.HARD;
        }

        if (score < 50) {

            if (currentDifficulty == Difficulty.HARD) {
                return Difficulty.MEDIUM;
            }

            if (currentDifficulty == Difficulty.MEDIUM) {
                return Difficulty.EASY;
            }

            return Difficulty.EASY;
        }

        // 50–79 → same difficulty
        return currentDifficulty;
    }

    @Override
    public StartInterviewResponse createSession(startInterviewRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResumeAnalysis resumeAnalysis = resumeAnalysisRepository.findByUser(user).orElseThrow(()->new DocumentNotFoundException("Resume Analysis not Found"));
        JDMatchAnalysis jdMatchAnalysis= jdAnalysisRepository.findByUser(user).orElseThrow(()->new DocumentNotFoundException("JD Analysis not found"));
        InterviewBluePrint bluePrint = interviewBluePrintRepository.findByIdAndUserId(request.bluePrintId(),user.getId()).orElseThrow(()->new InvalidBluePrint("Invalid Blueprint"));
        InterviewSession session = InterviewSession.builder()
                .user(user)
                        .interviewStatus(InterviewStatus.IN_PROGRESS)
                .bluePrint(bluePrint)
                                .currentDifficulty(Difficulty.EASY)
                                        .questionNumber(1).build();

        interviewSessionRepository.save(session);


        InterviewContext context = InterviewContext.builder()
                        .interviewBluePrint(bluePrint)
                                .resumeAnalysis(resumeAnalysis)
                                        .jdMatchAnalysis(jdMatchAnalysis)
                                                .interviewSession(session)
                                                        .build();

        GeneratedQuestion generatedQuestion =
                aiOrchasterator.generateFirstQuestion(context);

        InterviewQuestion question = InterviewQuestion.builder()
                .session(session)
                .question(generatedQuestion.question())
                .topic(generatedQuestion.topic())
                .difficulty(generatedQuestion.difficulty())
                .questionNumber(1)
                .build();

        InterviewQuestion savedQuestion =
                interviewQuestionRepository.save(question);

        return new StartInterviewResponse(
                session.getId(),
                savedQuestion.getId(),
                savedQuestion.getQuestion(),
                savedQuestion.getTopic(),
                savedQuestion.getDifficulty(),
                savedQuestion.getQuestionNumber()
        );


    }

    @Override
    public SubmitAnswerResponse submitAnswer(
            Long sessionId,
            SubmitAnswerRequest request) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        InterviewSession session = (InterviewSession) interviewSessionRepository
                .findByIdAndUserId(sessionId, user.getId())
                .orElseThrow(() ->
                        new DocumentNotFoundException("Interview session not found"));


        InterviewQuestion question = (InterviewQuestion) interviewQuestionRepository
                .findByIdAndSessionId(request.questionId(), sessionId)
                .orElseThrow(() ->
                        new DocumentNotFoundException("Interview question not found"));


        question.setAnswer(request.answer());


        AnswerEvaluation evaluation =
                aiOrchasterator.evaluateAnswer(
                        question.getQuestion(),
                        request.answer(),
                        question.getTopic(),
                        question.getDifficulty()
                );


        question.setScore(evaluation.score());
        question.setFeedback(evaluation.feedback());

        interviewQuestionRepository.save(question);


        Difficulty nextDifficulty =
                decideDifficulty(
                        session.getCurrentDifficulty(),
                        evaluation.score()
                );

        session.setCurrentDifficulty(nextDifficulty);
        session.setQuestionNumber(
                session.getQuestionNumber() + 1
        );

        interviewSessionRepository.save(session);

        //Build context for next question
        InterviewContext context = InterviewContext.builder()
                .resumeAnalysis(
                        resumeAnalysisRepository.findByUser(user)
                                .orElseThrow(() ->
                                        new DocumentNotFoundException(
                                                "Resume Analysis not found"))
                )
                .jdMatchAnalysis(
                        jdAnalysisRepository.findByUser(user)
                                .orElseThrow(() ->
                                        new DocumentNotFoundException(
                                                "JD Analysis not found"))
                )
                .interviewBluePrint(
                        session.getBluePrint()).build();


        GeneratedQuestion generatedQuestion =
                aiOrchasterator.generateNextQuestion(
                        context,
                        question,
                        evaluation
                );

        // Save next question
        InterviewQuestion nextQuestion = InterviewQuestion.builder()
                .session(session)
                .question(generatedQuestion.question())
                .topic(generatedQuestion.topic())
                .difficulty(nextDifficulty)
                .questionNumber(session.getQuestionNumber())
                .build();

        InterviewQuestion savedNextQuestion =
                interviewQuestionRepository.save(nextQuestion);

        // Return evaluation + next question
        return new SubmitAnswerResponse(
                session.getId(),

                question.getQuestionNumber(),
                evaluation.score(),
                evaluation.feedback(),

                savedNextQuestion.getId(),
                savedNextQuestion.getQuestion(),
                savedNextQuestion.getTopic(),
                savedNextQuestion.getDifficulty(),
                savedNextQuestion.getQuestionNumber()
        );

    }
}
