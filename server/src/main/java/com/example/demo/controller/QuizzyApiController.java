package com.example.demo.controller;

import com.example.demo.dto.AnswerDto;
import com.example.demo.services.Questions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class QuizzyApiController {

    Questions questionsGlobal = new Questions();
    List<List> answers = new ArrayList<>();

    int userPoints = 0 ;

    // Read file name
    @CrossOrigin
    @RequestMapping(value = "/quiz/category", method = RequestMethod.POST)
    public ResponseEntity<Void> readFile(@RequestBody Questions questions) throws IOException {
        questions.readQuestions();
        questionsGlobal = questions;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Return question for provided id
    @CrossOrigin
    @GetMapping("/quiz/question/{id}")
    public ResponseEntity<HashMap<String, Object>> getQuestion(@PathVariable("id") String id) {

        // Join answers in one array
        for (int i = 0; i < questionsGlobal.getQuestions().size(); i++) {
            List<String> ans = new ArrayList<>();
            ans.add(questionsGlobal.getQuestions().get(i).getAnswer1());
            ans.add(questionsGlobal.getQuestions().get(i).getAnswer2());
            ans.add(questionsGlobal.getQuestions().get(i).getAnswer3());
            ans.add(questionsGlobal.getQuestions().get(i).getAnswer4());
            answers.add(ans);
        }

        // Create returned JSON
        int index = Integer.parseInt(id);
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("question",questionsGlobal.getQuestions().get(index).getQuestion());
        returnedData.put("answers", answers.get(index));
        returnedData.put("points",questionsGlobal.getQuestions().get(index).getPoints());
        returnedData.put("lastQuestion",index == questionsGlobal.getQuestions().size() - 1);

        return ResponseEntity.ok(returnedData);
    }

    // Read answer from client
    @CrossOrigin
    @RequestMapping(value = "/quiz/calculate", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Void> checkAnswer(@RequestBody AnswerDto answer) {

    if (Arrays.equals(questionsGlobal.getQuestions().get(answer.getQuestionId()).getCorrect(), answer.getSelectedAnswers()))
        userPoints += questionsGlobal.getQuestions().get(answer.getQuestionId()).getPoints();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
