package com.example.quizzy.dto;

public class QuestionDto {

    private String question;
    private String[] answers;
    private Boolean lastQuestion;
    private int points;

    public String getQuestion(){ return question; }

    public String[] getAnswers(){ return answers; }

    public Boolean getLastQuestion(){ return lastQuestion; }

//    public int getPoints(){ return points; }
}
