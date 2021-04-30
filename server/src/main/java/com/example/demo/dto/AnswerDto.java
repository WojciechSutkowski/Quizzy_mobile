package com.example.demo.dto;

public class AnswerDto {
    private int questionId;
    private int[] selectedAnswers;
    private boolean lastQuestion;

    public int getQuestionId() {
        return questionId;
    }

    public int[] getSelectedAnswers() {
        return selectedAnswers;
    }

    public boolean isLastQuestion() {
        return lastQuestion;
    }
}
