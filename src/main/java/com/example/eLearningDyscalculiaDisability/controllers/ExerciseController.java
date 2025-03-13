package com.example.eLearningDyscalculiaDisability.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.eLearningDyscalculiaDisability.model.ExerciseAttempt;
import com.example.eLearningDyscalculiaDisability.model.Question;
import com.example.eLearningDyscalculiaDisability.service.ExerciseService;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


  @GetMapping
    public String newPage(Model model) {

        return "exercise"; 
    }

    @GetMapping("/questions")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestions(@RequestParam String category, @RequestParam String difficulty) {
        return ResponseEntity.ok(exerciseService.getQuestions(category, difficulty));
    }

    @PostMapping("/submitAttempt")
    @ResponseBody
    public ResponseEntity<ExerciseAttempt> submitAttempt(
            @RequestParam Long studentId,
            @RequestParam Long questionId,
            @RequestParam String selectedAnswer) {

        ExerciseAttempt attempt = exerciseService.submitAttempt(studentId, questionId, selectedAnswer);
        return ResponseEntity.ok(attempt);
    }

    
}
