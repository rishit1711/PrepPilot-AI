package com.example.PrepPilot.AI.entity;

import com.example.PrepPilot.AI.entity.enums.AnalysisStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumeAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long documentId;
    long userId;
    String modelName;
    @Enumerated(EnumType.STRING)
    AnalysisStatus analysisStatus;
    @CreationTimestamp
    Instant analyzedAt;
    @CreationTimestamp
    Instant createdAt;
    @UpdateTimestamp
    Instant updatedAt;
}
