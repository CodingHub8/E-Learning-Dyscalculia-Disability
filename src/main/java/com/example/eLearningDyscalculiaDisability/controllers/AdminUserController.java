package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminUserController {
    @GetMapping("/admin/manage_user")
    public String showManageUserPage() {
        return "/admin/manage_user";
    }
    
}
