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

    // TODO: Fix this error "Required request parameter 'username' for method parameter type String is not present"

    @GetMapping("/login")
    public Map<String, String> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpSession session) {
        boolean isAuthenticated = authenticate(username, password);
        Map<String, String> response = new HashMap<>();

        if (isAuthenticated) {
            session.setAttribute("username", username);
            response.put("message", "Login successful");
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

    @GetMapping("/me")
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

