package com.example.PrepPilot.AI.entity;

import com.example.PrepPilot.AI.entity.enums.Difficulty;
import com.example.PrepPilot.AI.entity.enums.InterviewStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterviewSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @ManyToOne
    InterviewBluePrint bluePrint;
    @Enumerated(EnumType.STRING)
    Difficulty currentDifficulty;
    @Enumerated(EnumType.STRING)
    InterviewStatus interviewStatus;
}

