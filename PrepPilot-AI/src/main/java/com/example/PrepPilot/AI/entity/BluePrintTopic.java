package com.example.PrepPilot.AI.entity;

import com.example.PrepPilot.AI.entity.enums.Difficulty;
import com.example.PrepPilot.AI.entity.enums.Priority;
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
public class BluePrintTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    Difficulty difficulty;
    Integer weightage;
    @Enumerated(EnumType.STRING)
    Priority priority;
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    BluePrintSection section;
}
