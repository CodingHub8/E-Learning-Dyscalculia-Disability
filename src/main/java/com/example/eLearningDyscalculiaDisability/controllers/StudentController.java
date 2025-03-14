package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.service.StudentService;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    // ✅ Get all students
    @GetMapping("/student")
    @ResponseBody
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // ✅ API endpoints for users
    @GetMapping("/api/users")
    @ResponseBody
    public List<Student> getAllUsers() {
        return studentService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<Student> getUserById(@PathVariable Long id) {
        Optional<Student> user = studentService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    @ResponseBody
    public Student createUser(@RequestBody Student user) {
        return studentService.createUser(user);
    }


    @PutMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<Student> updateUser(@PathVariable Long id, @RequestBody Student userDetails) {
        Student updatedUser = studentService.updateUser(id, userDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return studentService.deleteUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // ✅ Fetch user info from session
    @GetMapping("/api/session/user")
    @ResponseBody
    public ResponseEntity<Student> getUserFromSession(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(401).body(null);
        }
        Optional<Student> user = studentService.getUserById(studentId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Check if session exists
    @GetMapping("/api/session/check")
    @ResponseBody
    public ResponseEntity<String> checkSession(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        return studentId != null
                ? ResponseEntity.ok("Session active for Student ID: " + studentId)
                : ResponseEntity.status(401).body("No active session");
    }
}
