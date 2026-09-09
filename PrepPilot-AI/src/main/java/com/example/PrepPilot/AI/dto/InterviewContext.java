package com.example.PrepPilot.AI.dto;

import com.example.PrepPilot.AI.entity.InterviewBluePrint;
import com.example.PrepPilot.AI.entity.InterviewSession;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InterviewContext {
    private ResumeAnalysis resumeAnalysis;
    private JDMatchAnalysis jdMatchAnalysis;
    private InterviewBluePrint interviewBluePrint;
    private InterviewSession interviewSession;
}
