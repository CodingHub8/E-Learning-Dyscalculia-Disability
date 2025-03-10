package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.service.StudentService;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;

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

    // ✅ Page redirection (NO API prefix)
    @GetMapping("/student")
    @ResponseBody
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // ✅ API endpoints (Should be under `/api`)
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
}
