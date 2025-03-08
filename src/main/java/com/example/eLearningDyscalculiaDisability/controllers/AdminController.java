package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.repository.AdminRepository;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/admin")
    public String admin(String username, String password) {
        return "login"; // This maps to templates/index.html
    }
}
