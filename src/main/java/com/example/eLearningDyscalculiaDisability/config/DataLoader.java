package com.example.eLearningDyscalculiaDisability.config;

import com.example.eLearningDyscalculiaDisability.model.*;
import com.example.eLearningDyscalculiaDisability.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    public void run(String... args) throws Exception {// Load initial data into the database
        // Admin Data
        if (adminRepository.count() == 0) {
            adminRepository.save(new Admin("admin", "123", "admin@email.com", "rw"));
        }

        if (adminQuestionRepository.count() == 0) {
            // TODO: Add some data
        }

        if (exerciseAttemptRepository.count() == 0) {
            // TODO: Add some data
        }

          // Student Data
        if (studentRepository.count() == 0) {
            studentRepository.save(new Student("student", "student@email.com", "123"));
        }

        if(quizRepository.count() == 0) {
            quizRepository.save(new Quiz("addition", "What is 2 + 2?", "3, 4, 5, 6", "4"));
            quizRepository.save(new Quiz("subtraction", "What is 5 - 3?", "2, 4, 6, 8", "2"));
            quizRepository.save(new Quiz("multiplication", "What is 5 × 5?", "25, 10, 55, 15", "25"));
            quizRepository.save(new Quiz("division", "What is 20 ÷ 4?", "24, 4, 5, 2", "5"));
        }
    }
}
