package com.example.ibmtask.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Event event;

    @Column(length = 2000, nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    private Instant createdAt;

    //question
    @PrePersist
    public void onCreate() {
        createdAt = Instant.now();
    }
}
