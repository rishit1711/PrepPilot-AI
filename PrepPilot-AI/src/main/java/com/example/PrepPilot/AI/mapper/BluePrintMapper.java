package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.entity.InterviewBluePrint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BluePrintMapper {

    InterviewBluePrint toBluePrint(BluePrintResponse response);

}
