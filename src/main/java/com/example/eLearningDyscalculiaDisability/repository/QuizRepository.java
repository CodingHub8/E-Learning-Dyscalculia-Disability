package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTopic(String topic);
}
