package com.example.eLearningDyscalculiaDisability.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.eLearningDyscalculiaDisability.model.Admin;
import com.example.eLearningDyscalculiaDisability.model.Student;
import com.example.eLearningDyscalculiaDisability.repository.AdminQuestionRepository;
import com.example.eLearningDyscalculiaDisability.repository.AdminRepository;
import com.example.eLearningDyscalculiaDisability.repository.ExerciseAttemptRepository;
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final AdminQuestionRepository adminQuestionRepository;
    private final ExerciseAttemptRepository exerciseAttemptRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;

    public DataLoader(AdminRepository adminRepository, AdminQuestionRepository adminQuestionRepository,
                      ExerciseAttemptRepository exerciseAttemptRepository, StudentRepository studentRepository,
                      QuizRepository quizRepository) {

        this.adminRepository = adminRepository;
        this.adminQuestionRepository = adminQuestionRepository;
        this.exerciseAttemptRepository = exerciseAttemptRepository;
        this.studentRepository = studentRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    // @Override
    // public void run(String... args) throws Exception {// Load initial data into the database
    //     // // Admin Data
    //     // if (adminRepository.count() == 0) {
    //     //     adminRepository.save(new Admin("admin", "123", "admin@email.com", "rw"));
    //     // }

        // // Student Data
        // if (studentRepository.count() == 0) {
        //     studentRepository.save(new Student("student", "123", "student@email.com", "A"));
        // }

    }

