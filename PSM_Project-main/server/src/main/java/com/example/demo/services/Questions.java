package com.example.demo.services;

import com.example.demo.dto.QuestionDto;
import com.opencsv.bean.CsvToBeanBuilder;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questions {
    String category;
    List<QuestionDto> questions = new ArrayList<>();

    public String getCategory() {
        return category;
    }

    public void readQuestions() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("categories/"+this.category+".csv");
        System.out.println(category);

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            QuestionDto tmp = new QuestionDto(line.split(";"));
            this.questions.add(tmp);
        }
    }

    public QuestionDto getQuestion(String id)  {
        for(int i=0;i<this.questions.size();i++){
            if(this.questions.get(i).getId().equals(id)) {
                return this.questions.get(i);
            }
        }
        return null;
    }

}
