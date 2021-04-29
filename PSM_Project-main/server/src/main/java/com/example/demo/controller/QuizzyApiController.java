package com.example.demo.controller;

import com.example.demo.dto.QuestionDto;
import com.example.demo.services.Questions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class QuizzyApiController {

    Questions questionsGlobal = new Questions();

    // Read file name
    @CrossOrigin
    @RequestMapping(value = "/quiz/category", method = RequestMethod.POST)
    public ResponseEntity<Void> readFile(@RequestBody Questions questions) throws IOException {
        questions.readQuestions();
        questionsGlobal = questions;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get question by ID
    @CrossOrigin
    @GetMapping("/quiz/question/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable("id") String id) {
        return ResponseEntity.ok(questionsGlobal.getQuestion(id));
    }
}
