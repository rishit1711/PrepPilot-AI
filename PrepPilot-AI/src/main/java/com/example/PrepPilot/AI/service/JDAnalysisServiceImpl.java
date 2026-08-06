package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.JDAnalysisRequest;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.exception.ResumeNotFoundException;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JDAnalysisServiceImpl implements JDAnalysisService{

    private final AIOrchasterator aiOrchasterator;
    private final DocumentRepository documentRepository;

    @Override
    public JDAnalysisResponse analyzeResumeWithJD(JDAnalysisRequest request) {

        // security hanled -->> authenticated user ka resume aur jd do bas
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Document resume = documentRepository.findByIdAndUser(request.resume_id(),user).orElseThrow(null);
        Document jd = documentRepository.findByIdAndUser(request.jd_id(),user).orElseThrow(null);

        if(resume ==null|| jd==null){
            throw new ResumeNotFoundException("Please Provide Requried Documents");
        }
        JDAnalysisResponse response = aiOrchasterator.analyzeJd(resume,jd);
        return response;

    }
}
