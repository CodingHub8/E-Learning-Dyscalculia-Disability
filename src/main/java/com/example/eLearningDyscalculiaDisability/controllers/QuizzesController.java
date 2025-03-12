package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Quiz;
import com.example.eLearningDyscalculiaDisability.model.QuizResult;
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;
import com.example.eLearningDyscalculiaDisability.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class QuizzesController {
    
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    // Display Quiz Questions
    @GetMapping("/quizzes")
    public String showQuiz(@RequestParam String topic, Model model) {
        List<Quiz> quizzes = quizRepository.findByTopic(topic);
        model.addAttribute("topic", topic);
        model.addAttribute("quizzes", quizzes);
        return "quizzes";
    }@PostMapping("/submit-quiz")
    public ResponseEntity<?> submitQuiz(@RequestBody Map<String, Object> requestData) {
        try {
            System.out.println("Received Request Data: " + requestData);
    
            Long studentId = Long.valueOf(requestData.get("studentId").toString());
            String topic = requestData.get("topic").toString();
    
            List<Long> quizIds = ((List<?>) requestData.get("quizIds")).stream()
                    .map(obj -> Long.valueOf(obj.toString()))
                    .collect(Collectors.toList());
    
            List<String> answers = ((List<?>) requestData.get("answers")).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
    
            // Fetch quizzes from DB
            List<Quiz> quizzes = quizRepository.findAllById(quizIds);
            if (quizzes.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "No quizzes found for given IDs"));
            }
    
            int correctCount = 0;
            int totalQuestions = quizzes.size();
    
            // Check answers and save results in DB
            List<QuizResult> results = new ArrayList<>();
            for (int i = 0; i < totalQuestions; i++) {
                boolean isCorrect = answers.get(i).equalsIgnoreCase(quizzes.get(i).getCorrect_answer());
                if (isCorrect) {
                    correctCount++;
                }
    
                // ✅ Insert quiz result into the database
                QuizResult result = new QuizResult(studentId, quizzes.get(i).getId(), topic, isCorrect ? 1 : 0);
                results.add(result);
            }
    
            // ✅ Save all quiz results in the database
            quizResultRepository.saveAll(results);
    
            // ✅ Return JSON response
            return ResponseEntity.ok(Map.of(
                    "message", "Quiz submitted successfully!",
                    "score", correctCount,
                    "totalQuestions", totalQuestions
            ));
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error processing quiz submission: " + e.getMessage()));
        }
    }
    
}
