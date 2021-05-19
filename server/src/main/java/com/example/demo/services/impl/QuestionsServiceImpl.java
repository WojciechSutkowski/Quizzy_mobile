package com.example.demo.services.impl;

import com.example.demo.dto.QuestionDto;
import com.example.demo.services.QuestionService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionService {

    private List<QuestionDto> questions;

    @Override
    public List<QuestionDto> getQuestions() {
        return questions;
    }

    @Override
    public void readQuestions(String categoryName) throws IOException {
        questions = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("categories/"+categoryName+".csv");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            QuestionDto tmp = new QuestionDto(line.split(";"));
            this.questions.add(tmp);
        }
    }

    @Override
    public int getMaxPoints() {
        int maxPoints = 0;

        for(int i = 1; i < this.questions.size() ; i++) {
            maxPoints += this.questions.get(i).getPoints();
        }

        return maxPoints;
    }
}
