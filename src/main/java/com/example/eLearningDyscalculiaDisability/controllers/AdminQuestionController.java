package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminQuestionController {
    @GetMapping("/admin/update_question")
    public String showManageQuestionPage() {
        return "/admin/update_question";
    }
    
}
