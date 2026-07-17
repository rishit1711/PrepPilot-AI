package com.example.PrepPilot.AI.repository;

import com.example.PrepPilot.AI.entity.Document;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {

    List<Document> findByUser(User user);
    List<Document> findByUserAndDocumentType(DocumentType documentType);
}
