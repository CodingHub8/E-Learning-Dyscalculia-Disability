package com.example.eLearningDyscalculiaDisability.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

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

    @Column(columnDefinition = "TEXT")
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

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Convert JSON string to List<String>
    public List<String> getAnswers() {
        try {
            return objectMapper.readValue(answers, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            return null;
        }
    }

    // Convert List<String> to JSON string
    public void setAnswers(List<String> answers) {
        try {
            this.answers = objectMapper.writeValueAsString(answers);
        } catch (IOException e) {
            this.answers = "[]";
        }
    }
}
