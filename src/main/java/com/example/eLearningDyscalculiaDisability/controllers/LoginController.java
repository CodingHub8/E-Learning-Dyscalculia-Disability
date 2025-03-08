package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

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
        Optional<Student> studentOpt = studentRepository.findByUsername(username);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // Compare plain text passwords (for simplicity, not recommended for production)
            if (password.equals(student.getPassword())) {
                session.setAttribute("username", username); // Store username in session
                return new RedirectView("/mainpage"); // Redirect to dashboard after successful login
            } else {
                redirectAttributes.addAttribute("error", "true");
                return new RedirectView("/login"); // Redirect back to login with error
            }
        } else {
            redirectAttributes.addAttribute("error", "true");
            return new RedirectView("/login"); // Redirect back to login with error
        }
    }

    //This method to handle logout and clear session
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Invalidate the session (clear all session data)
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
        return new RedirectView("/login"); // Redirect to login page after logout
    }
  
    @GetMapping("/session")
    @ResponseBody
    public Map<String, String> getSession(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            response.put("username", username);
        } else {
            response.put("message", "Not authenticated");
        }
        return response;
    }
}