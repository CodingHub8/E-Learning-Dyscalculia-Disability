package com.example.eLearningDyscalculiaDisability.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eLearningDyscalculiaDisability.dto.ExerciseAttemptDTO;
import com.example.eLearningDyscalculiaDisability.service.ExerciseAttemptService;

@RestController
@RequestMapping("/api/exercise-attempts")
public class ExerciseAttemptController {
    @Autowired
    private ExerciseAttemptService attemptService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<ExerciseAttemptDTO>> getStudentAttempts(@PathVariable Long studentId) {
        List<ExerciseAttemptDTO> attempts = attemptService.getAttemptsByStudent(studentId);
        
        // Debugging: Log the fetched data
        System.out.println("Fetched attempts for student " + studentId + ": " + attempts);
        
        return ResponseEntity.ok(attempts);
    }
}
