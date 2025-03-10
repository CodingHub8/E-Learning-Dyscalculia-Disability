package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminQuestionRepository extends JpaRepository<Question, Long> {

        List<Question> findByCategoryAndDifficulty(String category, String difficulty);

}

