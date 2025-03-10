package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity @Table(name = "quiz")
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "topic")
    private String topic;//addition, subtraction, multiplication, division

    @Column(name = "question")
    private String question;

    @Column(name = "answers")
    private String answers;// use comma (,) delimiter

    @Column(name = "correct_answer")
    private String correct_answer;

    public Quiz() {}

    public Quiz(String topic, String question, String answers, String correct_answer) {
        this.topic = topic;
        this.question = question;
        this.answers = answers;
        this.correct_answer = correct_answer;
    }
}
