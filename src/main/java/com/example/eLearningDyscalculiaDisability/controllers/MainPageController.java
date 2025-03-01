package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/mainpage")
    public String newPage(Model model) {
        // Add any model attributes if needed
        return "mainpage"; // This corresponds to newpage.html in the templates directory
    }
}
