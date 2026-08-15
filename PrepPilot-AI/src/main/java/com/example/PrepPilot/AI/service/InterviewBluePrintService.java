package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;

public interface InterviewBluePrintService {
    BluePrintResponse GenerateBluePrint(BluePrintRequest request);
}
