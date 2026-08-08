package com.example.PrepPilot.AI.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
    public class ResumeEducation {

        private String degree;

        private String institution;

        private String year;
    }
