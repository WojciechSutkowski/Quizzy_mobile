package com.example.demo.dto;

import java.util.Arrays;

public class QuestionDto {

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int[] correct;
    private int points;

    public QuestionDto(String[] arr) {
        this.id = arr[0];
        this.question = arr[1];
        this.answer1 = arr[2];
        this.answer2 = arr[3];
        this.answer3 = arr[4];
        this.answer4 = arr[5];

        if (!arr[6].equals("CORRECT_ANSWERS")){
            this.correct = Arrays.stream(arr[6].split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
        }

        if (!arr[7].equals("POINTS")){
            this.points = Integer.parseInt(arr[7]);
        }

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

    public int[] getCorrect() {
        return correct;
    }

    public int getPoints() {
        return points;
    }
}
