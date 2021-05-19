package com.example.demo.services;

import com.example.demo.dto.QuestionDto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Questions {
    String category;
    List<QuestionDto> questions = new ArrayList<>();

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public String getCategory() {
        return category;
    }

    public void readQuestions() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("categories/"+this.category+".csv");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            QuestionDto tmp = new QuestionDto(line.split(";"));
            this.questions.add(tmp);
        }
    }
}
