package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PerformanceController {
    @GetMapping("/performance")
    public String showPerformacePage() {
        return "performance";
    }
    
}
