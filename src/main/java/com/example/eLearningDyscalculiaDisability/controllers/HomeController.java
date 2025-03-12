package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @GetMapping("/")  // When user visits "/", return index.html
    public String home() {
        return "index"; // This maps to templates/index.html
    }
}
