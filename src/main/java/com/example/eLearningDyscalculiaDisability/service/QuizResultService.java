package com.example.eLearningDyscalculiaDisability.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eLearningDyscalculiaDisability.dto.QuizSummary; // Ensure this DTO exists
import com.example.eLearningDyscalculiaDisability.model.QuizResult;
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;
// Import repository and model
import com.example.eLearningDyscalculiaDisability.repository.QuizResultRepository;

@Service
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;

    public QuizResultService(QuizResultRepository quizResultRepository, QuizRepository quizRepository) {
        this.quizResultRepository = quizResultRepository;
        this.quizRepository = quizRepository;
    }

    // Get total quizzes and completed quizzes for a student
    public Map<LocalDate, QuizSummary> getQuizResultsGroupedByDate(Long studentId) {
        int totalQuizzes = (int) quizRepository.count(); // Total quizzes available

        List<QuizResult> results = quizResultRepository.findByStudentId(studentId);

        return results.stream()
            .collect(Collectors.groupingBy(
                result -> result.getSubmittedAt().toLocalDate(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        int completedQuizzes = (int) list.stream()
                            .map(QuizResult::getQuizId)
                            .distinct()
                            .count(); // Count distinct completed quizzes

                        return new QuizSummary(totalQuizzes, completedQuizzes);
                    }
                )
            ));
    }

    // Get total assigned quizzes for a student
    public long countTotalAssignedQuizzes(Long studentId) {
        return quizRepository.count(); // Total quizzes assigned
    }

    // Get total completed quizzes for a student
    public long countCompletedQuizzes(Long studentId) {
        return quizResultRepository.findByStudentId(studentId).stream()
            .map(QuizResult::getQuizId)
            .distinct()
            .count();
    }

    public List<QuizResult> getAllResults() {
        return quizResultRepository.findAll();
    }
}