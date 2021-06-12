package com.example.quizzy.dto;

public class AnswersDto {

    private int questionId;
    private int[] selectedAnswers;
    private boolean lastQuestion;

    public AnswersDto(int[] selectedAnswers, int questionId,boolean lastQuestion) {
        this.questionId = questionId;
        this.selectedAnswers = selectedAnswers;
        this.lastQuestion =  lastQuestion;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setSelectedAnswers(int[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public boolean isLastQuestion() {
        return lastQuestion;
    }


}
