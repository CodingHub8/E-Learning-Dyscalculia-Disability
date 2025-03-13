package com.example.eLearningDyscalculiaDisability.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    private String difficulty;

    @Column(columnDefinition = "TEXT")  // Store as JSON in DB
    private String options;

    @Column(nullable = false)
    private String correctAnswer;  // Store "A", "B", "C", or "D"

    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Convert JSON string to List<String>
    public List<String> getOptions() {
        try {
            return objectMapper.readValue(options, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            return null;
        }
    }

    // Convert List<String> to JSON string
    public void setOptions(List<String> options) {
        try {
            this.options = objectMapper.writeValueAsString(options);
        } catch (IOException e) {
            this.options = "[]";
        }
    }
}