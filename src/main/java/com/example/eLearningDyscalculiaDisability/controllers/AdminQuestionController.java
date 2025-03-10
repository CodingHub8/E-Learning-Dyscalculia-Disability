package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Question;
import com.example.eLearningDyscalculiaDisability.service.AdminQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class AdminQuestionController {

    @Autowired
    private AdminQuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        // Ensure options are properly formatted before returning
        questions.forEach(q -> q.setOptions(q.getOptions()));
        return questions;
    }

    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        question.ifPresent(q -> q.setOptions(q.getOptions()));
        return question;
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        question.setOptions(question.getOptions()); // Convert options to JSON before saving
        return questionService.addQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setOptions(question.getOptions()); // Ensure options are converted
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
