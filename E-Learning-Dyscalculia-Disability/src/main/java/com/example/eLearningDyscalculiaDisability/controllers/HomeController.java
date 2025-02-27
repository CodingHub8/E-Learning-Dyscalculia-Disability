package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // When user visits "/", return index.html
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the E-Learning Platform, Blyat!");
        return "index"; // This maps to templates/index.html
    }
}
