package com.example.eLearningDyscalculiaDisability.service;

import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public Optional<Student> getUserById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createUser(Student user) {
        return studentRepository.save(user);
    }

    public Student updateUser(Long id, Student userDetails) {
        return studentRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setGradeLevel(userDetails.getGradeLevel());

            return studentRepository.save(user);
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


