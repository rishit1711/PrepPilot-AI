package com.example.PrepPilot.AI.ai;

import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
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


}
