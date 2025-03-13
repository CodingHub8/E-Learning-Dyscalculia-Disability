package com.example.eLearningDyscalculiaDisability.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.service.StudentService;

@Controller // Ensures proper redirection handling
@RequestMapping("/signup")
public class SignupController {

    private final StudentService studentService;

    @Autowired
    public SignupController(StudentService studentService) {
        this.studentService = studentService;
    }

    //  Handles GET request for the signup page
    @GetMapping
    public String showSignupPage() {
        return "signup"; // Must have a corresponding signup.html or signup.jsp
    }

    //  Handles POST request for form submission
    @PostMapping
    public String registerStudent(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "gradeLevel", required = false) String gradeLevel,
            RedirectAttributes redirectAttributes) {

        // Default gradeLevel if null
        if (gradeLevel == null || gradeLevel.trim().isEmpty()) {
            gradeLevel = "Not Specified";
        }

        // Create and save the student
        Student student = new Student();
        student.setUsername(username);
        student.setEmail(email);
        student.setPassword(password);
        student.setGradeLevel(gradeLevel);

        studentService.createUser(student);

        // ✅ Add success message & redirect
        redirectAttributes.addFlashAttribute("successMessage", "Account created successfully!");
        return "redirect:/signup";

    }
}