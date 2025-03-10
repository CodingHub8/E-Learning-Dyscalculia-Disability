package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity @Table(name = "quiz")
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topic;//addition, subtraction, multiplication, division

    @Column
    private String question;

    @Column
    private String answers;// use comma (,) delimiter

    @Column
    private String correct_answer;

    public Quiz() {}

    public Quiz(String topic, String question, String answers, String correct_answer) {
        this.topic = topic;
        this.question = question;
        this.answers = answers;
        this.correct_answer = correct_answer;
    }
}
