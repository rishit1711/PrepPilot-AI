package com.example.PrepPilot.AI.config;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Splitter {

    @Bean
    public TokenTextSplitter tokenTextSplitter(){
        return new TokenTextSplitter();
    }
}
