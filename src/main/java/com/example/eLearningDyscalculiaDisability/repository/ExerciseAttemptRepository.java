package com.example.eLearningDyscalculiaDisability.repository;

import com.example.eLearningDyscalculiaDisability.model.ExerciseAttempt;
import com.example.eLearningDyscalculiaDisability.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseAttemptRepository extends JpaRepository<ExerciseAttempt, Long> {
    
    // Find all attempts by a specific student
    List<ExerciseAttempt> findByStudentId(Long studentId);
    
    // Count the number of attempts a student has made for a specific question
    int countByStudentIdAndQuestion(Long studentId, Question question);
}

