package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BluePrintServiceImpl implements BluePrintService{
    private final DocumentRepository documentRepository;
    @Override
    public BluePrintResponse GenerateBluePrint(BluePrintRequest request) {

    }
}
