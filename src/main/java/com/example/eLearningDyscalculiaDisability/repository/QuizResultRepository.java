package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByStudentId(Long studentId);
}
