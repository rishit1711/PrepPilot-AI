package com.example.PrepPilot.AI.Orchasterator;

import com.example.PrepPilot.AI.Orchasterator.promptBuilder.BluePrintPromptBuilder;
import com.example.PrepPilot.AI.Orchasterator.promptBuilder.JDPromptBuilder;
import com.example.PrepPilot.AI.Orchasterator.promptBuilder.ResumePromptBuilder;
import com.example.PrepPilot.AI.ai.LLMService;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import com.example.PrepPilot.AI.exception.AIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIOrchasterator {

    private final VectorStore vectorStore;
    private final JDPromptBuilder jdPromptBuilder;
    private final ResumePromptBuilder resumePromptBuilder;
    private final LLMService llmService;
    private final ObjectMapper objectMapper;
    private final BluePrintPromptBuilder bluePrintPromptBuilder;


    // =========================
    // RESUME ANALYSIS
    // =========================

    public ResumeAnalysisResponse analyze(Document document) {

        List<org.springframework.ai.document.Document> chunks =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("resume")
                                .topK(20)
                                .build()
                );

        if (chunks == null || chunks.isEmpty()) {
            throw new AIException("No resume content found for document");
        }

        Prompt prompt = resumePromptBuilder.build(chunks);

        String rawResponse = llmService.generate(prompt.toString());



        // Remove markdown code fences
        rawResponse = rawResponse
                .replace("```json", "")
                .replace("```", "")
                .trim();

        ResumeAnalysisResponse response;

        try {
            response = objectMapper.readValue(
                    rawResponse,
                    ResumeAnalysisResponse.class
            );
        } catch (Exception e) {
            throw new AIException(
                    "AI returned invalid JSON: " + rawResponse
            );
        }

        // Validations
        if (response.summary() == null) {
            throw new AIException("Summary section could not be null");
        }

        if (response.skills() == null) {
            throw new AIException("Skills section could not be null");
        }

        if (response.education() == null) {
            throw new AIException("Education section could not be null");
        }

        if (response.experiences() == null) {
            throw new AIException("Experiences section could not be null");
        }

        if (response.projects() == null) {
            throw new AIException("Projects section could not be null");
        }

        return response;
    }


    // =========================
    // JD MATCH ANALYSIS
    // =========================

    public JDAnalysisResponse analyzeJd(Document resume, Document jd) {

        List<org.springframework.ai.document.Document> resumeChunks =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("technical skills projects experience education achievements")
                                .topK(20)
                                .filterExpression(
                                        "documentId == \"" + resume.getId() + "\""
                                )
                                .build()
                );

        List<org.springframework.ai.document.Document> jdChunks =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("required skills responsibilities qualifications experience")
                                .topK(20)
                                .filterExpression(
                                        "documentId == \"" + jd.getId() + "\""
                                )
                                .build()
                );

        Prompt prompt = jdPromptBuilder.build(
                resumeChunks,
                jdChunks
        );

        return llmService.getAnalysis(prompt);
    }

    public BluePrintResponse generateBluePrint(Document document, ResumeAnalysis resumeAnalysis, JDMatchAnalysis jdMatchAnalysis) {
        List<org.springframework.ai.document.Document> jdChunks =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("required skills responsibilities qualifications experience")
                                .topK(20)
                                .filterExpression(
                                        "documentId == \"" + document.getId() + "\""
                                )
                                .build()
                );
        Prompt bluePrintprompt = bluePrintPromptBuilder.build(
                jdChunks.toString(),
                resumeAnalysis,
                jdMatchAnalysis
        );

        return llmService.getBluePrint(bluePrintprompt);
    }
}