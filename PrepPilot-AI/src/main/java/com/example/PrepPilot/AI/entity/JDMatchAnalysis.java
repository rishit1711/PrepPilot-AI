package com.example.PrepPilot.AI.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jd_match_analysis")
public class JDMatchAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Document resume;

    @ManyToOne
    private Document jobDescription;

    private Integer overallMatchScore;
    @Column(columnDefinition = "TEXT")
    private String conciseSummary;

    @ElementCollection
    private Set<String> matchingSkills = new HashSet<>();

    @ElementCollection
    private Set<String> missingSkills = new HashSet<>();

    @ElementCollection
    private Set<String> strengths = new HashSet<>();

    @ElementCollection
    private Set<String> weaknesses = new HashSet<>();

    @ElementCollection
    private Set<String> interviewFocusAreas = new HashSet<>();

    @ElementCollection
    private Set<String> actionableSuggestions = new HashSet<>();

    @CreationTimestamp
    private Instant createdAt;
}