package com.example.PrepPilot.AI.ai;

import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.dto.JDAnalysisResponse;
import com.example.PrepPilot.AI.dto.ResumeAnalysisResponse;
import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.JDMatchAnalysis;
import com.example.PrepPilot.AI.entity.ResumeAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
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
        System.out.println("NVIDIA_API_KEY length: " +
                (System.getenv("NVIDIA_API_KEY") != null ? System.getenv("NVIDIA_API_KEY").length() : "NULL"));
        return chatClient
                .prompt(prompt)
                .call()
                .entity(JDAnalysisResponse.class);


    }
    public BluePrintResponse getBluePrint(Prompt prompt) {

        ChatResponse chatResponse = chatClient.prompt(prompt)
                .call()
                .chatResponse();

        if (chatResponse == null) {
            throw new IllegalStateException("ChatResponse is null");
        }

        String content = chatResponse.getResult()
                .getOutput()
                .getText();

        System.out.println("========== AI RESPONSE ==========");
        System.out.println(content);
        System.out.println("========== RESPONSE LENGTH ==========");
        System.out.println(content != null ? content.length() : 0);

        return chatClient.prompt(prompt)
                .call()
                .entity(BluePrintResponse.class);
    }
}
