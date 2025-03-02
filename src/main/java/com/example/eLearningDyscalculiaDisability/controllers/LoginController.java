package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class LoginController {
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Make sure this matches your login HTML file name
    }

    @PostMapping("/login/auth")
    @ResponseBody
    public Map<String, String> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        boolean isAuthenticated = authenticate(username, password);
        Map<String, String> response = new HashMap<>();

        if (isAuthenticated) {
            session.setAttribute("username", username);
            response.put("message", "Login successful");
            response.put("token", UUID.randomUUID().toString()); // Simulating a token
        } else {
            response.put("message", "Invalid credentials");
        }
        return response;
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
