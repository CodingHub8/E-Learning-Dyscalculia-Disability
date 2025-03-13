package com.example.eLearningDyscalculiaDisability.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.logging.Logger;

import com.example.eLearningDyscalculiaDisability.service.QuizResultService;

@Controller
public class MainPageController {

    private final QuizResultService quizResultService;
    private static final Logger LOGGER = Logger.getLogger(MainPageController.class.getName());

    @Autowired
    public MainPageController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @GetMapping("/mainpage")
    public String newPage(HttpSession session, Model model) {
        Object studentIdObj = session.getAttribute("studentId");

        if (studentIdObj == null) {
            LOGGER.warning("Unauthorized access attempt to mainpage. Redirecting to login.");
            return "redirect:/login";
        }

        Long studentId = (Long) studentIdObj;

        // Logging for debugging
        LOGGER.info("Mainpage accessed by studentId: " + studentId);

        long totalQuizzes = quizResultService.countTotalAssignedQuizzes(studentId);
        long completedQuizzes = quizResultService.countCompletedQuizzes(studentId);

        model.addAttribute("totalQuizzes", totalQuizzes);
        model.addAttribute("completedQuizzes", completedQuizzes);

        return "mainpage";
    }
}
