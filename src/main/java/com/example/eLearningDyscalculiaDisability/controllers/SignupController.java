package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> signupStudent(@RequestParam String fullName,
                                             @RequestParam String email,
                                             @RequestParam String password) {
        // Save Student data to the database
        Student Student = new Student(fullName, email, password);
        StudentRepository.save(Student);

        return ResponseEntity.ok("Signup successful! Student saved to DB.");
    }
}
