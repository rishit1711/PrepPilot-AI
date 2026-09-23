package com.example.PrepPilot.AI.entity;

import com.example.PrepPilot.AI.entity.enums.ClaimStatus;
import com.example.PrepPilot.AI.entity.enums.ClaimType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResumeClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String claim;
    @Enumerated(EnumType.STRING)
    private ClaimType claimType;
    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    private Double confidenceScore;
    @ManyToOne
    private ResumeAnalysis resumeAnalysis;

}
