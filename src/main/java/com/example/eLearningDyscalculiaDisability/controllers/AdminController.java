package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Admin;
import com.example.eLearningDyscalculiaDisability.repository.AdminRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AdminController {
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/admin")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
