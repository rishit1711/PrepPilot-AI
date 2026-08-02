package com.example.PrepPilot.AI.Orchasterator;

import com.example.PrepPilot.AI.Orchasterator.promptBuilder.ResumePromptBuilder;
import com.example.PrepPilot.AI.ai.LLMService;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.exception.AIException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIOrchasterator {  // AI pipeline hai ye
        private final VectorStore vectorStore;
        private final ResumePromptBuilder resumePromptBuilder;
        private final LLMService llmService;

    public ResumeAnalysisResponse analyze(Document document) {
        List<org.springframework.ai.document.Document> chunks=vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query("resume")
                        .filterExpression("documentId == \"" + document.getId() + "\"")
                        .build()
        );
        Prompt prompt = resumePromptBuilder.build(chunks);
        ResumeAnalysisResponse resumeAnalysisResponse = llmService.generate(String.valueOf(prompt));


        // Validations
        if(resumeAnalysisResponse.summary()==null){
            throw new AIException("Summary section Could not be null");
        }
        if(resumeAnalysisResponse.skills()==null){
            throw new AIException("Skills  section Could not be null");
        }
        if(resumeAnalysisResponse.education()==null){
            throw new AIException("Education  section Could not be null");
        }
        if(resumeAnalysisResponse.experiences()==null){
            throw new AIException("Experiences  section Could not be null");
        }
        if(resumeAnalysisResponse.projects()==null){
            throw new AIException("Projects  section  Could not be null");
        }

        return resumeAnalysisResponse;



    }
}
