package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Quiz;  // Updated model to Quiz
import com.example.eLearningDyscalculiaDisability.service.AdminQuizService; // Updated service to AdminQuizService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")  // Changed endpoint to "/api/quizzes"
@CrossOrigin(origins = "*")
public class AdminQuizController {

    @Autowired
    private AdminQuizService quizService;  // Updated service to AdminQuizService

    // Get all quizzes
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        // Ensure options are properly formatted before returning
        quizzes.forEach(q -> q.setAnswers(q.getAnswers()));
        return quizzes;
    }

    // Get a quiz by its ID
    @GetMapping("/{id}")
    public Optional<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        quiz.ifPresent(q -> q.setAnswers(q.getAnswers()));
        return quiz;
    }

    // Add a new quiz
    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        quiz.setAnswers(quiz.getAnswers()); // Convert options to JSON before saving
        return quizService.addQuiz(quiz);
    }

    // Update an existing quiz
    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        quiz.setAnswers(quiz.getAnswers()); // Ensure options are converted
        return quizService.updateQuiz(id, quiz);
    }

    // Delete a quiz
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }
}
