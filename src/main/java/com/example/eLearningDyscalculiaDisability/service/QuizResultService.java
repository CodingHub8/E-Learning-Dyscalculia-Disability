package com.example.eLearningDyscalculiaDisability.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Import repository and model
import com.example.eLearningDyscalculiaDisability.repository.QuizResultRepository;
import com.example.eLearningDyscalculiaDisability.model.QuizResult;
import com.example.eLearningDyscalculiaDisability.dto.QuizSummary; // Ensure this DTO exists

@Service
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;

    public QuizResultService(QuizResultRepository quizResultRepository) {
        this.quizResultRepository = quizResultRepository;
    }

    public Map<LocalDate, QuizSummary> getQuizResultsGroupedByDate() {
        List<QuizResult> results = quizResultRepository.findAll();

        return results.stream()
            .collect(Collectors.groupingBy(
                result -> result.getSubmittedAt().toLocalDate(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        int correctCount = (int) list.stream().filter(q -> q.getIsCorrect() == 1).count();
                        int total = list.size();
                        return new QuizSummary(total, correctCount);
                    }
                )
            ));
    }

    public List<QuizResult> getAllResults() {
        return quizResultRepository.findAll();
    }
}    
