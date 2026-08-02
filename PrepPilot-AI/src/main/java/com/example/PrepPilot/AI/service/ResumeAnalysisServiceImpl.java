package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeRequest;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.ResumeNotFoundException;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeAnalysisServiceImpl implements ResumeAnalysisService {
    private final AIOrchasterator aiOrchasterator;
    private final DocumentRepository documentRepository;
    @Override
    public ResumeAnalysisResponse analyzeResume(ResumeRequest resumeRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Document document = documentRepository.findByIdAndUser(resumeRequest.id(),user).orElseThrow(()->new ResourceNotFoundException("Document not Found"));

        if(document.getDocumentType()!= DocumentType.RESUME){
            throw new ResumeNotFoundException("Given Document is not a Resume");
        }
        aiOrchasterator.analyze(document);

    }
}
