package com.example.PrepPilot.AI.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ClaimQuestion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(columnDefinition = "TEXT")
        private String question;

        @ManyToOne
        private ResumeClaim resumeClaim;
    }

