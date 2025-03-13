package com.example.eLearningDyscalculiaDisability.service;

import com.example.eLearningDyscalculiaDisability.repository.AdminQuestionRepository;
// import com.example.eLearningDyscalculiaDisability.repository.QuizAttemptRepository;
import com.example.eLearningDyscalculiaDisability.repository.StudentRepository;
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;
import com.example.eLearningDyscalculiaDisability.repository.QuizResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminDashboardService {

    @Autowired

    private StudentRepository studentRepository;

    @Autowired
    private AdminQuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    // @Autowired
    // private QuizAttemptRepository quizAttemptRepository;

    public Map<String, Object> getDashboardData() {
        Map<String, Object> dashboardData = new HashMap<>();

        long totalUsers = studentRepository.count();
        long totalQuestions = questionRepository.count();
        long totalQuizzes = quizRepository.count();
        long quizzesAttempted = quizResultRepository.count();

        // long quizzesAttempted = 0;
        double avgScore = 0;

        dashboardData.put("totalUsers", totalUsers);
        dashboardData.put("totalQuestions", totalQuestions);
        dashboardData.put("totalQuizzes", totalQuizzes);
        dashboardData.put("quizzesAttempted", quizzesAttempted);
        dashboardData.put("avgScore", avgScore);

        return dashboardData;
    }
}
