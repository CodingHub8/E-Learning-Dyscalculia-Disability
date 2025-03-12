package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.eLearningDyscalculiaDisability.service.QuizResultService;
import com.example.eLearningDyscalculiaDisability.service.ExerciseAttemptService;
import com.example.eLearningDyscalculiaDisability.dto.QuizSummary;
import com.example.eLearningDyscalculiaDisability.dto.ExerciseAttemptDTO;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    private final QuizResultService quizResultService;
    private final ExerciseAttemptService exerciseAttemptService;

    @Autowired
    public PerformanceController(QuizResultService quizResultService, ExerciseAttemptService exerciseAttemptService) {
        this.quizResultService = quizResultService;
        this.exerciseAttemptService = exerciseAttemptService;
    }

    @GetMapping
    public String showPerformancePage(Model model) {
        // Fetch quiz results grouped by date
        Map<LocalDate, QuizSummary> quizResults = quizResultService.getQuizResultsGroupedByDate();

        // Fetch exercise (MCQ) attempts
        List<ExerciseAttemptDTO> exerciseAttempts = exerciseAttemptService.getAllAttempts();

        // Pass data to the view
        model.addAttribute("quizResults", quizResults);
        model.addAttribute("exerciseAttempts", exerciseAttempts);

        return "performance"; // Renders performance.html
    }
}
