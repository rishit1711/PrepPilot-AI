package com.example.PrepPilot.AI.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
    public class ResumeExperience {

        private String company;

        private String role;

        private String duration;

        @Column(columnDefinition = "TEXT")
        private String description;
    }

