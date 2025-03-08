package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultControlller {
    @GetMapping("/result")
    public String newPage(Model model) {
        // Add any model attributes if needed
        return "result"; // This corresponds to newpage.html in the templates directory
    }
}
