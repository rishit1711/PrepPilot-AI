package com.example.PrepPilot.AI.ai;

import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMService {
    private final ChatClient chatClient;

    public String generate(String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();


    }


    public JDAnalysisResponse getAnalysis(Prompt prompt) {
        return chatClient
                .prompt(prompt)
                .call()
                .entity(JDAnalysisResponse.class);


    }
    public BluePrintResponse getBluePrint(Prompt prompt){
        return chatClient.prompt(prompt)
                .call().entity(BluePrintResponse.class);
    }
}
