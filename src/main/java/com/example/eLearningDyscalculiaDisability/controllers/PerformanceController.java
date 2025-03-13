package com.example.eLearningDyscalculiaDisability.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eLearningDyscalculiaDisability.dto.ExerciseAttemptDTO;
import com.example.eLearningDyscalculiaDisability.dto.QuizSummary;
import com.example.eLearningDyscalculiaDisability.service.ExerciseAttemptService;
import com.example.eLearningDyscalculiaDisability.service.QuizResultService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    private final QuizResultService quizResultService;
    private final ExerciseAttemptService exerciseAttemptService;
    private final HttpSession session;

    @Autowired
    public PerformanceController(QuizResultService quizResultService, 
                                 ExerciseAttemptService exerciseAttemptService, 
                                 HttpSession session) {
        this.quizResultService = quizResultService;
        this.exerciseAttemptService = exerciseAttemptService;
        this.session = session;
    }

    @GetMapping
    public String showPerformancePage(Model model) {
        Long studentId = getLoggedInStudentId(); // Get the logged-in student's ID

        if (studentId == null) {
            return "redirect:/login"; // Redirect if no student ID is found
        }

        // Fetch data only for the logged-in student
        Map<LocalDate, QuizSummary> quizResults = quizResultService.getQuizResultsGroupedByDate(studentId);
        List<ExerciseAttemptDTO> exerciseAttempts = exerciseAttemptService.getAttemptsByStudent(studentId);

        // Debugging: Print data to ensure correct fetching
        System.out.println("Student ID: " + studentId);
        System.out.println("Quiz Results: " + (quizResults == null || quizResults.isEmpty() ? "No data" : quizResults));
        System.out.println("Exercise Attempts: " + (exerciseAttempts == null || exerciseAttempts.isEmpty() ? "No data" : exerciseAttempts));

        // Ensure quizResults is null if empty
        if (quizResults == null || quizResults.isEmpty()) {
            model.addAttribute("quizResults", null);
        } else {
            model.addAttribute("quizResults", quizResults);
        }

        model.addAttribute("exerciseAttempts", exerciseAttempts);

        return "performance"; // Ensure "performance.html" exists inside "templates/"
    }

    private Long getLoggedInStudentId() {
        Object studentId = session.getAttribute("studentId");
        if (studentId instanceof Long) {
            return (Long) studentId;
        }
        return null; // Return null if studentId is not found in the session
    }
}
