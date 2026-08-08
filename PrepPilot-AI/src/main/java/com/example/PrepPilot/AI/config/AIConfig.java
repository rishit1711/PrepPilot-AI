package com.example.PrepPilot.AI.config;

import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
    public class AIConfig {
        @Bean
        public org.springframework.ai.chat.client.ChatClient chatClient(org.springframework.ai.chat.client.ChatClient.Builder builder){
            return builder
                    .defaultAdvisors(new SimpleLoggerAdvisor())
                    .build();
        }
    }

