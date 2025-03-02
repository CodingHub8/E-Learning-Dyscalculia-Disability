package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @PostMapping("/signup")
    public String showSignupPage() {
        return "/signup";
    }
    
}
