package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private StudentRepository studentRepository;

    // Show Profile Page with Student Info
    @GetMapping
    public String showProfilePage(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");

        if (studentId == null) {
            return "redirect:/login";
        }

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        studentOpt.ifPresent(student -> model.addAttribute("student", student));

        return "profile"; // Ensure this file exists in templates/
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword,
                                HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
    
        if (studentId == null) {
            return "redirect:/login";
        }
    
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setUsername(username);
            student.setEmail(email);
    
            // Ensure passwords match before updating
            if (!password.isEmpty()) {
                if (!password.equals(confirmPassword)) {
                    model.addAttribute("errorMessage", "Passwords do not match!");
                    model.addAttribute("student", student);
                    return "profile";
                }
                student.setPassword(password); // Apply password hashing in real implementation
            }
    
            studentRepository.save(student);
            model.addAttribute("student", student);
            model.addAttribute("successMessage", "Profile updated successfully!");
        } else {
            model.addAttribute("errorMessage", "User not found!");
        }
    
        return "profile";
    }
    
}
