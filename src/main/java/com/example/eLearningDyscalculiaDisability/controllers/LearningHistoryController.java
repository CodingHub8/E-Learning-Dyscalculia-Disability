package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LearningHistoryController {
    @GetMapping("/history")
    public String newPage(Model model) {
        // Add any model attributes if needed
        return "history"; // This corresponds to newpage.html in the templates directory
    }
}

