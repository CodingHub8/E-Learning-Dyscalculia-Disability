package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExplainationControllers {
    @GetMapping("/explaination")
    public String newPage(Model model) {
        // Add any model attributes if needed
        return "explaination"; // This corresponds to newpage.html in the templates directory
    }
}
