package com.example.PrepPilot.AI.entity;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
    public class ResumeProject {

        private String title;

        @Column(columnDefinition = "TEXT")
        private String description;

        @ElementCollection
        private List<String> technologies;
    }

