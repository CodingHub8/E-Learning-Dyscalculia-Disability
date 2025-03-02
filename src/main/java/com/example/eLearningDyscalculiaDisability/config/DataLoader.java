package com.example.eLearningDyscalculiaDisability.config;

import com.example.eLearningDyscalculiaDisability.model.*;
import com.example.eLearningDyscalculiaDisability.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;

    public DataLoader(AdminRepository adminRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {// Load initial data into the database
        // Admin Data
        if (adminRepository.count() == 0) {
            adminRepository.save(new Admin("admin", "123", "admin@email.com", "rw"));
        }

        // Student Data
        if (studentRepository.count() == 0) {
            studentRepository.save(new Student("student", "123", "student@email.com", "A"));
        }
    }
}
