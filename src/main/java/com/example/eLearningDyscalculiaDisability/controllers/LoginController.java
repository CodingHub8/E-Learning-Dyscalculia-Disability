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
    public RedirectView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (authenticate(username, password)) {
            session.setAttribute("username", username);
            return new RedirectView("/"); // Redirect to home page
        } else {
            redirectAttributes.addAttribute("error", "true");
            return new RedirectView("/login"); // Redirect back to login with error
        }
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

    public boolean authenticate(String username, String password) {
        Optional<Student> studentOpt = studentRepository.findByUsername(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return password.equals(student.getPassword());
        }
        return false;
    }
}
