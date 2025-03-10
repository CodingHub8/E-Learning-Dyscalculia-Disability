package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByTopic(String topic);
}
