package com.example.quizzy.dto;

public class AnswersDto {

    private int questionId;
    private int[] selectedAnswers;

    public AnswersDto(int[] selectedAnswers) {
        this.questionId = getQuestionId();
        this.selectedAnswers = selectedAnswers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setSelectedAnswers(int[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

}
