package com.example.demo.dto;

import com.opencsv.bean.CsvBindByPosition;

public class QuestionDto {

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correct;
    private String points;

    public QuestionDto(String[] arr) {
        this.id = arr[0];
        this.question = arr[1];
        this.answer1 = arr[2];
        this.answer2 = arr[3];
        this.answer3 = arr[4];
        this.answer4 = arr[5];
        this.correct = arr[6];
        this.points = arr[7];
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrect() {
        return correct;
    }

    public String getPoints() {
        return points;
    }
}
