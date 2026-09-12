package com.example.PrepPilot.AI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BluePrintSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    Integer sequence;

    @Column(nullable = false)
    Integer weightage;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BluePrintTopic> topics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "blueprint_id", nullable = false)
    InterviewBluePrint bluePrint;
}