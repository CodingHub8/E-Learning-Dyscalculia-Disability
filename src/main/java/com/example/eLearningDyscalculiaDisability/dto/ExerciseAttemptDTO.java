package com.example.eLearningDyscalculiaDisability.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.eLearningDyscalculiaDisability.model.ExerciseAttempt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values in JSON response
public class ExerciseAttemptDTO {
    
    @JsonProperty("attempt_id")
    private Long attemptId;

    @JsonProperty("question_category")
    private String questionCategory;

    @JsonProperty("question_difficulty")
    private String questionDifficulty;

    private String question;
    private List<String> options;

    @JsonProperty("selected_answer")
    private String selectedAnswer;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    @JsonProperty("is_correct")
    private boolean isCorrect;

    @JsonProperty("attempt_count")
    private int attemptCount;

    private int score;

    @JsonProperty("date_attempted")
    private LocalDateTime dateAttempted;

    // No-args constructor for Jackson deserialization
    public ExerciseAttemptDTO() {}

    // Constructor to convert Entity to DTO
    public ExerciseAttemptDTO(ExerciseAttempt attempt) {
        if (attempt != null && attempt.getQuestion() != null) {
            this.attemptId = attempt.getId();
            this.questionCategory = attempt.getQuestion().getCategory();
            this.questionDifficulty = attempt.getQuestion().getDifficulty();
            this.question = attempt.getQuestion().getQuestion();
            this.options = attempt.getQuestion().getOptions();
            this.selectedAnswer = attempt.getSelectedAnswer();
            this.correctAnswer = attempt.getQuestion().getCorrectAnswer();
            this.isCorrect = attempt.isCorrect();
            this.attemptCount = attempt.getAttemptCount();
            this.score = attempt.getScore();
            this.dateAttempted = attempt.getDateAttempted();
        }
    }

    // New Constructor for Direct DTO Creation
    public ExerciseAttemptDTO(Long attemptId, String questionCategory, String questionDifficulty, 
                              String question, List<String> options, String selectedAnswer, 
                              String correctAnswer, boolean isCorrect, int attemptCount, 
                              int score, LocalDateTime dateAttempted) {
        this.attemptId = attemptId;
        this.questionCategory = questionCategory;
        this.questionDifficulty = questionDifficulty;
        this.question = question;
        this.options = options;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
        this.attemptCount = attemptCount;
        this.score = score;
        this.dateAttempted = dateAttempted;
    }

    // Getters
    public Long getAttemptId() {
        return attemptId;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public String getQuestionDifficulty() {
        return questionDifficulty;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getDateAttempted() {
        return dateAttempted;
    }

    // Setters
    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public void setQuestionDifficulty(String questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDateAttempted(LocalDateTime dateAttempted) {
        this.dateAttempted = dateAttempted;
    }
}
