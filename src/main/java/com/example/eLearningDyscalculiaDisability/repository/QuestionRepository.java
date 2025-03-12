package com.example.eLearningDyscalculiaDisability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eLearningDyscalculiaDisability.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
