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

import java.util.*;

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
    public RedirectView login(@RequestParam("fullName") String fullName,
                              @RequestParam("password") String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (authenticate(fullName, password)) {
            session.setAttribute("fullName", fullName);
            return new RedirectView("/"); // Redirect to home page
        } else {
            redirectAttributes.addAttribute("error", "true");
            return new RedirectView("/login"); // Redirect back to login with error
        }
    }

    public boolean authenticate(String fullName, String password) {
        Optional<Student> studentOpt = studentRepository.findByFullName(fullName);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return password.equals(student.getPassword());
        }
        return false;
    }

    @PostMapping("/me")
    public Map<String, String> getStudent(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        String fullName = (String) session.getAttribute("fullName");

        if (fullName != null) {
            response.put("fullName", fullName);
        } else {
            response.put("message", "Not authenticated");
        }
        return response;
    }
}
