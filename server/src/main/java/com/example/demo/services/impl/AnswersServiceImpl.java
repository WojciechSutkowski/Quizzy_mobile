package com.example.demo.services.impl;

import com.example.demo.dto.AnswerDto;
import com.example.demo.services.AnswersService;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private QuestionService questionService;

    private List<List<String>> userAnswers = new ArrayList<>();

    private List<String> isCorrect = new ArrayList<>();

    private int userPoints = 0;

    @Override
    public void addAnswer(AnswerDto answerDto) {

        // Array of user answers
        List<String> selectedAnswers = new ArrayList<>();
        for (int k = 0 ; k < answerDto.getSelectedAnswers().length ; k++)
            selectedAnswers.add(questionService.getQuestions().get(answerDto.getQuestionId()).getAnswers().get(answerDto.getSelectedAnswers()[k]-1));
        userAnswers.add(selectedAnswers);

        //   Check if selected answers equals correct answers - if yes increase user points - add yes/no to isCorrect array
        if (Arrays.equals(questionService.getQuestions().get(answerDto.getQuestionId()).getCorrect(), answerDto.getSelectedAnswers())){
            userPoints += questionService.getQuestions().get(answerDto.getQuestionId()).getPoints();
            isCorrect.add("Yes");
        }
        else isCorrect.add("No");
    }

    public List<List<String>> getUserAnswers() {
        return userAnswers;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public List<String> getIsCorrect() {
        return isCorrect;
    }

    public void resetAnswers() {
        userAnswers = new ArrayList<>();
        isCorrect = new ArrayList<>();
        userPoints = 0 ;
    }
}
