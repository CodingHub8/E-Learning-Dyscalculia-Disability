package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final StudentRepository StudentRepository;

    // Inject the repository via constructor
    public SignupController(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    @GetMapping
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping
    @ResponseBody
    public RedirectView signupStudent(@RequestParam String username,
                                             @RequestParam String email,
                                             @RequestParam String password,
                                             HttpSession session) {
        // Save Student data to the database
        StudentRepository.save(new Student(username, email, password));
        session.setAttribute("username", username);
        return new RedirectView("/"); // Redirect to home page
    }
}
