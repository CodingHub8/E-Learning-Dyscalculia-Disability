package com.example.eLearningDyscalculiaDisability.service;

import com.example.eLearningDyscalculiaDisability.model.Question;
import com.example.eLearningDyscalculiaDisability.repository.AdminQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminQuestionService {

    @Autowired
    private AdminQuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestion(questionDetails.getQuestion());
            existingQuestion.setDifficulty(questionDetails.getDifficulty());
            existingQuestion.setOptions(questionDetails.getOptions());  // ✅ Update options
            existingQuestion.setCorrectAnswer(questionDetails.getCorrectAnswer());
            return questionRepository.save(existingQuestion);
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
