package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingupController {
    @GetMapping("/singup")
    public String showSingupPage() {
        return "/singup";
    }
    
}
