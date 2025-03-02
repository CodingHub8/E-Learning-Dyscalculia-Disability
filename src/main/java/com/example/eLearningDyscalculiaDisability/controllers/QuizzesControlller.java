package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizzesControlller {
    @GetMapping("/quizzes")
    public String newPage(Model model) {
        // Add any model attributes if needed
        return "quizzes"; // This corresponds to newpage.html in the templates directory
    }
}
