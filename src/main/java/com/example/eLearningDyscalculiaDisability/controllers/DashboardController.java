package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DashboardController {
    @GetMapping("/admin/dashboard")
    public String showDashboardPage() {
        return "/admin/dashboard";
    }
    
}
