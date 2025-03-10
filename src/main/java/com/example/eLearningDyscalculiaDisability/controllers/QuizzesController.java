package com.example.eLearningDyscalculiaDisability.controllers;

import com.example.eLearningDyscalculiaDisability.model.Quiz;
import com.example.eLearningDyscalculiaDisability.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizzesController {
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/quizzes")
    public String showQuiz(@RequestParam String topic, Model model) {
        // Fetch questions from the database based on the topic
        List<Quiz> quizzes = quizRepository.findByTopic(topic);

        // Add topic and quiz questions to the model
        model.addAttribute("topic", topic);
        model.addAttribute("quizzes", quizzes);
        return "quizzes";
    }
}
