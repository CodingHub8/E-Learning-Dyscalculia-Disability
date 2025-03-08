package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Admin;
import com.example.eLearningDyscalculiaDisability.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class AdminLoginController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login"; // Ensure this matches your login HTML file
    }

    @PostMapping(value = "/admin/login/auth", consumes = "application/x-www-form-urlencoded")
    public RedirectView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Compare plain text passwords (for simplicity, not recommended for production)
            if (password.equals(admin.getPassword())) {
                session.setAttribute("username", username); // Store username in session
                return new RedirectView("/admin/dashboard"); // Redirect to dashboard after successful login
            } else {
                redirectAttributes.addAttribute("error", "true");
                return new RedirectView("/admin/login"); // Redirect back to login with error
            }
        } else {
            redirectAttributes.addAttribute("error", "true");
            return new RedirectView("/admin/login"); // Redirect back to login with error
        }
    }

    //This method to handle logout and clear session
    @GetMapping("/admin/logout")
    public RedirectView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Invalidate the session (clear all session data)
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
        return new RedirectView("/admin/login"); // Redirect to login page after logout
    }
}
