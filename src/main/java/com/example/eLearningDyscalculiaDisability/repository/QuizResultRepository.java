package com.example.eLearningDyscalculiaDisability.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eLearningDyscalculiaDisability.model.QuizResult;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByStudentId(Long studentId);
}
