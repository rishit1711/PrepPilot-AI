package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.GeneratedQuestion;
import com.example.PrepPilot.AI.dto.StartInterviewResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewResponseMapper {

    StartInterviewResponse toInterviewResponse(GeneratedQuestion generatedQuestion);
}
