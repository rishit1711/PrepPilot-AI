package com.example.PrepPilot.AI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Embeddable
@Setter
@Getter
public class ResumeProject {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String technologies;

}