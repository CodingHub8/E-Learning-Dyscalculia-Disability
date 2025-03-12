package com.example.eLearningDyscalculiaDisability.dto; // **Pastikan package ikut folder!**

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AdminQuestionDTO {
    private Long id;
    private String question;
    private String category;
    private String difficulty;
    private List<String> options;
    private String correctAnswer;
}