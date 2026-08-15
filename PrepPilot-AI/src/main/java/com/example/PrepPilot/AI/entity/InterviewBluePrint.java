package com.example.PrepPilot.AI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterviewBluePrint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false)
    Long resumeId;
    @Column(nullable = false)
    Long jd_id;
    @Column(nullable = false)
    Integer totalQuestions;
    Instant createdAt;
    @OneToMany
    List<BluePrintSection> sections;


}
