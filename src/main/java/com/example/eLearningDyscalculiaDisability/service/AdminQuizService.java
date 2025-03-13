package com.example.eLearningDyscalculiaDisability.service;

import com.example.eLearningDyscalculiaDisability.model.Quiz;  // Change to use Quiz model
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;  // Update to use Quiz repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminQuizService {

    @Autowired
    private QuizRepository quizRepository;  // Repository for quiz

    // Fetch all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();  // Get all quizzes from the database
    }

    // Get a quiz by its ID
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);  // Fetch quiz by ID
    }

    // Add a new quiz
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);  // Save new quiz to the database
    }

    // Update an existing quiz
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);  // Check if quiz exists
        if (optionalQuiz.isPresent()) {
            Quiz existingQuiz = optionalQuiz.get();
            existingQuiz.setTopic(quizDetails.getTopic());
            existingQuiz.setQuestion(quizDetails.getQuestion());  // Update questions list
            existingQuiz.setAnswers(quizDetails.getAnswers());  // ✅ Update options
            existingQuiz.setCorrect_answer(quizDetails.getCorrect_answer());            
            return quizRepository.save(existingQuiz);  // Save updated quiz to the database
        }
        return null;  // If quiz not found, return null
    }

    // Delete a quiz by its ID
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);  // Delete quiz from the database
    }
}
