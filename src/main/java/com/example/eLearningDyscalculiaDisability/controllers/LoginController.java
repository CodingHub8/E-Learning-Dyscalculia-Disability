package com.example.eLearningDyscalculiaDisability.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.eLearningDyscalculiaDisability.model.Admin;
import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.AdminRepository;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository; // Add Admin repository

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password!");
        }
        return "login"; // Ensure this matches your login HTML file
    }

    @PostMapping(value = "/login/auth", consumes = "application/x-www-form-urlencoded")
    public RedirectView login(@RequestParam("username") String username, 
                              @RequestParam("password") String password, 
                              HttpSession session, 
                              RedirectAttributes redirectAttributes) {
        // 🔹 Check if the user is a student
        Optional<Student> studentOpt = studentRepository.findByUsername(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (student.getPassword().equals(password)) {
                session.setAttribute("userId", student.getId());
                session.setAttribute("username", student.getUsername());
                session.setAttribute("role", "student"); // Mark role as student

                LOGGER.info("Student logged in: " + student.getUsername() + " (ID: " + student.getId() + ")");
                return new RedirectView("/mainpage"); // Redirect students
            }
        }

        // 🔹 If not a student, check if the user is an admin
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (admin.getPassword().equals(password)) {
                session.setAttribute("userId", admin.getId());
                session.setAttribute("username", admin.getUsername());
                session.setAttribute("role", "admin"); // Mark role as admin

                LOGGER.info("Admin logged in: " + admin.getUsername() + " (ID: " + admin.getId() + ")");
                return new RedirectView("/admin/dashboard"); // Redirect admins
            }
        }

        // 🔹 If neither student nor admin found, return to login with error
        LOGGER.warning("Login failed: User " + username + " not found or incorrect password");
        redirectAttributes.addAttribute("error", "true");
        return new RedirectView("/login");
    }

    // Admin Logout
@GetMapping("/admin/logout")
public RedirectView adminLogout(HttpSession session, RedirectAttributes redirectAttributes) {
    LOGGER.info("Admin logged out: " + session.getAttribute("username"));
    session.invalidate(); // Clear session

    redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
    return new RedirectView("/login"); // Redirect to login page
}


    // Logout and clear session
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        LOGGER.info("User logged out: " + session.getAttribute("username"));
        session.invalidate();
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
        return new RedirectView("/login");
    }

    // Check session data
    @GetMapping("/session")
    @ResponseBody
    public Map<String, String> getSession(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String username = (String) session.getAttribute("username");
        String userId = (session.getAttribute("userId") != null) ? session.getAttribute("userId").toString() : null;
        String role = (String) session.getAttribute("role");

        if (username != null && userId != null) {
            response.put("username", username);
            response.put("userId", userId);
            response.put("role", role);
        } else {
            response.put("message", "Not authenticated");
        }
        return response;
    }
}
