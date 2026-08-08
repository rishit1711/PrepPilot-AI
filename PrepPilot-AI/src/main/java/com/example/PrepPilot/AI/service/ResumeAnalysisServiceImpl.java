package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.Orchasterator.AIOrchasterator;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeRequest;
import com.example.PrepPilot.AI.entity.*;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import com.example.PrepPilot.AI.exception.ResourceNotFoundException;
import com.example.PrepPilot.AI.exception.ResumeNotFoundException;
import com.example.PrepPilot.AI.mapper.ProfileMapper;
import com.example.PrepPilot.AI.repository.DocumentRepository;
import com.example.PrepPilot.AI.repository.ProfileRepository;
import com.example.PrepPilot.AI.repository.ResumeAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeAnalysisServiceImpl implements ResumeAnalysisService {
    private final AIOrchasterator aiOrchasterator;
    private final DocumentRepository documentRepository;
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ResumeAnalysisRepository resumeAnalysisRepository;
    @Override
    public ResumeAnalysisResponse analyzeResume(ResumeRequest resumeRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Document document = documentRepository.findByIdAndUser(resumeRequest.id(),user).orElseThrow(()->new ResourceNotFoundException("Document not Found"));

        if(document.getDocumentType()!= DocumentType.RESUME){
            throw new ResumeNotFoundException("Given Document is not a Resume");
        }
        ResumeAnalysisResponse response=aiOrchasterator.analyze(document);

//        Profile profile = profileMapper.toEntity(response);
//        profileRepository.save(profile);

        ResumeAnalysis analysis = new ResumeAnalysis();
        analysis.setResume(document);
        analysis.setSummary(response.summary());
        analysis.setSkills(response.skills());
        analysis.setProjects(
                response.projects().stream()
                        .map(dto -> {
                            ResumeProject p = new ResumeProject();
                            p.setTitle(dto.title());
                            p.setDescription(dto.description());
                            p.setTechnologies(String.valueOf(dto.technologies()));
                            return p;
                        })
                        .toList()
        );
        analysis.setExperiences(
                response.experiences().stream()
                        .map(dto -> {
                            ResumeExperience e = new ResumeExperience();

                            e.setCompany(dto.company());
                            e.setRole(dto.role());
                            e.setDuration(dto.duration());
                            e.setDescription(dto.description());

                            return e;
                        })
                        .toList()
        );
        analysis.setEducation(
                response.education().stream()
                        .map(dto -> {
                            ResumeEducation e = new ResumeEducation();

                            e.setDegree(dto.degree());
                            e.setInstitution(dto.institution());
                            e.setYear(dto.year());

                            return e;
                        })
                        .toList()
        );
        analysis.setStrengths(response.strengths());
        analysis.setWeaknesses(response.weaknesses());
        analysis.setKeywords(response.keywords());
        analysis.setAtsScore(response.atsScore());
        analysis.setSeniority(response.seniority());
        analysis.setSuggestedRoles(response.suggestedRoles());

        return  response;

    }
}
