package com.example.demo.controller;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.services.AnswersService;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

@RestController
public class QuizzyApiController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswersService answersService;

    // Read file name
    @CrossOrigin
    @PostMapping(value = "/quiz/category")
    public ResponseEntity<?> readFile(@RequestBody CategoryDto categoryDto) throws IOException {

        questionService.readQuestions(categoryDto.getCategory());
        answersService.resetAnswers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Return question for provided id
    @CrossOrigin
    @GetMapping("/quiz/question/{id}")
    public ResponseEntity<HashMap<String, Object>> getQuestion(@PathVariable("id") String id) {

        // Create returned JSON
        int index = Integer.parseInt(id);
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("question", questionService.getQuestions().get(index).getQuestion());
        returnedData.put("answers", questionService.getQuestions().get(index).getAnswers());
        returnedData.put("points", questionService.getQuestions().get(index).getPoints());
        returnedData.put("lastQuestion",index == questionService.getQuestions().size() - 1);

        return ResponseEntity.ok(returnedData);
    }

    // Read answer from client
    @CrossOrigin
    @PutMapping("/quiz/calculate")
    public @ResponseBody ResponseEntity<Void> checkAnswer(@RequestBody AnswerDto answer) {

        // Add selected answer to answers array
        answersService.addAnswer(answer);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    // Generate results in PDF
    @CrossOrigin
    @GetMapping("/quiz/report")
    public ResponseEntity<HashMap<String, Object>> getResults() {

        String result = answersService.getUserPoints() >= (questionService.getMaxPoints() / 2) ? "Passed" : "Failed" ;

        // Create returned JSON
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("result",result);
        returnedData.put("userPoints",answersService.getUserPoints());
        returnedData.put("maxPoints",questionService.getMaxPoints());

        return ResponseEntity.ok(returnedData);
    }
}
