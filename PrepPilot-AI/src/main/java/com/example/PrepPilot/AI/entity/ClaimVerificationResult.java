package com.example.PrepPilot.AI.entity;

import com.example.PrepPilot.AI.entity.enums.ClaimStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimVerificationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ResumeClaim resumeClaim;

    private Double confidenceScore;

    @Enumerated(EnumType.STRING)
    private
    ClaimStatus status;

    @Column(columnDefinition = "TEXT")
    private String evidence;

    @Column(columnDefinition = "TEXT")
    private String knowledgeGaps;
}