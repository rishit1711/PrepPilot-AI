package com.example.PrepPilot.AI.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
    public class ResumeAnalysis {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        private Document resume;

        @Column(columnDefinition = "TEXT")
        private String summary;

        private Integer atsScore;

        private String seniority;

        @ElementCollection
        private List<String> skills;

        @ElementCollection
        private List<String> strengths;

        @ElementCollection
        private List<String> weaknesses;

        @ElementCollection
        private List<String> keywords;

        @ElementCollection
        private List<String> suggestedRoles;

        @ElementCollection
        private List<ResumeProject> projects;

        @ElementCollection
        private List<ResumeExperience> experiences;

        @ElementCollection
        private List<ResumeEducation> education;
    }

