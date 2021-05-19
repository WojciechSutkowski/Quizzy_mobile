package com.example.demo.services;

import com.example.demo.dto.AnswerDto;

import java.util.List;

public interface AnswersService {

    void addAnswer(AnswerDto answerDto);
    void resetAnswers();

    List<List<String>> getUserAnswers();
    List<String> getIsCorrect();

    int getUserPoints();
}
