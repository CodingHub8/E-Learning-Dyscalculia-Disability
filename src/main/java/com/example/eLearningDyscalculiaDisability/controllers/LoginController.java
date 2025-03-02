package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Make sure this matches your login HTML file name
    }

    @PostMapping(value = "/login/auth", consumes = "application/x-www-form-urlencoded")
    public RedirectView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (authenticate(username, password)) {
            session.setAttribute("username", username);
            return new RedirectView("/"); // Redirect to the home page
        } else {
            return new RedirectView("/login?error=true"); // Redirect back to login with error message
        }
    }

    public boolean authenticate(String username, String password) {
        Optional<Student> studentOpt = studentRepository.findByUsername(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return password.equals(student.getPassword());
        }
        return false;
    }

    @PostMapping("/me")
    public Map<String, String> getStudent(HttpSession session) {
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
