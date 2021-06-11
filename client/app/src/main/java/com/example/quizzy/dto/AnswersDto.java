package com.example.quizzy.dto;

public class AnswersDto {

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
