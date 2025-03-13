package com.example.eLearningDyscalculiaDisability.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quiz_results")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;  // Stores the ID of the quiz question

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "is_correct", nullable = false)
    private int isCorrect; // 1 for correct, 0 for incorrect

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    public QuizResult() {
        this.submittedAt = LocalDateTime.now();
    }

    public QuizResult(Long studentId, Long quizId, String topic, int isCorrect) {
        this.studentId = studentId;
        this.quizId = quizId;
        this.topic = topic;
        this.isCorrect = isCorrect;
        this.submittedAt = LocalDateTime.now();
    }
}
