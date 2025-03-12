package com.example.eLearningDyscalculiaDisability.dto;

public class QuizSummary {
    private int totalQuestions;
    private int correctAnswers;

    public QuizSummary(int totalQuestions, int correctAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return totalQuestions - correctAnswers;
    }

    public double getScorePercentage() {
        return totalQuestions == 0 ? 0 : (correctAnswers * 100.0) / totalQuestions;
    }
}
