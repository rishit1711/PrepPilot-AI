package com.example.PrepPilot.AI.ai;

import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMService {
    private final ChatClient chatClient;

    public ResumeAnalysisResponse generate(String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(ResumeAnalysisResponse.class);

    }


    public JDAnalysisResponse getAnalysis(Prompt prompt) {
        String response = chatClient
                .prompt(prompt)
                .call()
                .content();
        System.out.println(response);
        return null;
    }
}
