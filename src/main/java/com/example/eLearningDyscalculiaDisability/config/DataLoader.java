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
            // Addition Questions
            quizRepository.save(new Quiz("addition", "What is 2 + 2?", "3,4,5,6", "4"));
            quizRepository.save(new Quiz("addition", "What is 7 + 3?", "9,10,11,12", "10"));
            quizRepository.save(new Quiz("addition", "What is 15 + 8?", "21,23,25,27", "23"));
            quizRepository.save(new Quiz("addition", "What is 19 + 6?", "24,25,26,27", "25"));

            // Subtraction Questions
            quizRepository.save(new Quiz("subtraction", "What is 5 - 3?", "1,2,3,4", "2"));
            quizRepository.save(new Quiz("subtraction", "What is 12 - 5?", "5,6,7,8", "7"));
            quizRepository.save(new Quiz("subtraction", "What is 20 - 9?", "10,11,12,13", "11"));
            quizRepository.save(new Quiz("subtraction", "What is 25 - 13?", "10,12,14,16", "12"));

            // Multiplication Questions
            quizRepository.save(new Quiz("multiplication", "What is 2 × 3?", "4,5,6,7", "6"));
            quizRepository.save(new Quiz("multiplication", "What is 5 × 4?", "15,20,25,30", "20"));
            quizRepository.save(new Quiz("multiplication", "What is 7 × 6?", "42,48,49,52", "42"));
            quizRepository.save(new Quiz("multiplication", "What is 9 × 8?", "63,72,81,90", "72"));

            // Division Questions
            quizRepository.save(new Quiz("division", "What is 10 ÷ 2?", "2,3,4,5", "5"));
            quizRepository.save(new Quiz("division", "What is 21 ÷ 3?", "6,7,8,9", "7"));
            quizRepository.save(new Quiz("division", "What is 36 ÷ 6?", "4,5,6,7", "6"));
            quizRepository.save(new Quiz("division", "What is 49 ÷ 7?", "5,6,7,8", "7"));
        }
    }
}
