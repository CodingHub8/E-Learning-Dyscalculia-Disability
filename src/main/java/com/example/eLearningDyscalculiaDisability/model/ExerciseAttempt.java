package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercise_attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false) // Establishes FK relationship
    private Question question;

    private String selectedAnswer;
    private boolean isCorrect;
    private int attemptCount;
    private int score;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateAttempted = LocalDateTime.now();
}
