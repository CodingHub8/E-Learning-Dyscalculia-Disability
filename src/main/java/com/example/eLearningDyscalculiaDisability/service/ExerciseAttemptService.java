package com.example.eLearningDyscalculiaDisability.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eLearningDyscalculiaDisability.dto.ExerciseAttemptDTO;
import com.example.eLearningDyscalculiaDisability.model.ExerciseAttempt;
import com.example.eLearningDyscalculiaDisability.repository.ExerciseAttemptRepository;

@Service
public class ExerciseAttemptService {
    @Autowired
    private ExerciseAttemptRepository attemptRepository;

    // Fetch all attempts and convert to DTO
    public List<ExerciseAttemptDTO> getAllAttempts() {
        List<ExerciseAttempt> attempts = attemptRepository.findAll();
        return attempts.stream().map(ExerciseAttemptDTO::new).collect(Collectors.toList());
    }

    // Fetch attempts by student ID
    public List<ExerciseAttemptDTO> getAttemptsByStudent(Long studentId) {
        List<ExerciseAttempt> attempts = attemptRepository.findByStudentId(studentId);
        return attempts.stream().map(ExerciseAttemptDTO::new).collect(Collectors.toList());
    }
}
