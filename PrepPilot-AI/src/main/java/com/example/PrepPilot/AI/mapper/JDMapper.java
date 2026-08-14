package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JDMapper {

    JDMatchAnalysis toEntity(JDAnalysisResponse response);

    JDAnalysisResponse toResponse(JDMatchAnalysis analysis);
}