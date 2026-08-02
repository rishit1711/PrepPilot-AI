package com.example.PrepPilot.AI.Orchasterator;

import com.example.PrepPilot.AI.entity.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIOrchasterator {  // AI pipeline hai ye
        private final VectorStore vectorStore;

    public void analyze(Document document) {
        List<org.springframework.ai.document.Document> chuks=vectorStore.similaritySearch(
                SearchRequest.builder()
                        .filterExpression("documentId == 15")
                        .build()
        );
    }
}
