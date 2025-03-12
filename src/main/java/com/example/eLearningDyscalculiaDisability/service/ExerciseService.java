package com.example.eLearningDyscalculiaDisability.service;

import com.example.eLearningDyscalculiaDisability.model.ExerciseAttempt;
import com.example.eLearningDyscalculiaDisability.model.Question;
import com.example.eLearningDyscalculiaDisability.repository.ExerciseAttemptRepository;
import com.example.eLearningDyscalculiaDisability.repository.AdminQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    
    private final AdminQuestionRepository questionRepository;
    private final ExerciseAttemptRepository exerciseAttemptRepository;

    public ExerciseService(AdminQuestionRepository questionRepository, ExerciseAttemptRepository exerciseAttemptRepository) {
        this.questionRepository = questionRepository;
        this.exerciseAttemptRepository = exerciseAttemptRepository;
    }

    // Fetch questions based on category and difficulty
    public List<Question> getQuestions(String category, String difficulty) {
        return questionRepository.findByCategoryAndDifficulty(category, difficulty);
    }

    // Save student attempt
    public ExerciseAttempt submitAttempt(Long studentId, Long questionId, String selectedAnswer) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);

        if (questionOpt.isEmpty()) {
            throw new RuntimeException("Question not found");
        }

        Question question = questionOpt.get();
        boolean isCorrect = selectedAnswer.equalsIgnoreCase(question.getCorrectAnswer());

        // Get previous attempts count
        int previousAttempts = exerciseAttemptRepository.countByStudentIdAndQuestion(studentId, question);
        int newAttemptCount = previousAttempts + 1;
        
        // Calculate score (e.g., 1 point for correct answer)
        int score = isCorrect ? 1 : 0;

        // Save attempt
        ExerciseAttempt attempt = new ExerciseAttempt();
        attempt.setStudentId(studentId);
        attempt.setQuestion(question);
        attempt.setSelectedAnswer(selectedAnswer);
        attempt.setCorrect(isCorrect);
        attempt.setAttemptCount(newAttemptCount);
        attempt.setScore(score);


        return exerciseAttemptRepository.save(attempt);
    }
}
