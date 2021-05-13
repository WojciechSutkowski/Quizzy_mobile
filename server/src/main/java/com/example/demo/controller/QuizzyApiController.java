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

    // Read questions ( data - question.title , question.answers etc.)
    Questions questionsGlobal = new Questions();

    // User points
    int userPoints;

    // User answers
    List<List<String>> userAnswers;

    // Yes/no if correct answer or no
    List<String> isCorrect;

    // Max points
    int maxPoints;

    // Read file name
    @CrossOrigin
    @RequestMapping(value = "/quiz/category", method = RequestMethod.POST)
    public ResponseEntity<Void> readFile(@RequestBody Questions questions) throws IOException {

        // Read question from file method
        questions.readQuestions();

        // Set userPoints
        userPoints = 0;

        // Set empty user answers
        userAnswers = new ArrayList<>();

        // Set/Empty isCorrect
        isCorrect = new ArrayList<>();

        // Assign read question into global question so other functions can use it
        questionsGlobal = questions;

        // Calculate max points for current category
        for(int i = 1 ; i < questions.getQuestions().size() ; i++) {
            maxPoints += questions.getQuestions().get(i).getPoints();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Return question for provided id
    @CrossOrigin
    @GetMapping("/quiz/question/{id}")
    public ResponseEntity<HashMap<String, Object>> getQuestion(@PathVariable("id") String id) {

        // Create returned JSON
        int index = Integer.parseInt(id);
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("question",questionsGlobal.getQuestions().get(index).getQuestion());
        returnedData.put("answers",questionsGlobal.getQuestions().get(index).getAnswers());
        returnedData.put("points",questionsGlobal.getQuestions().get(index).getPoints());
        returnedData.put("lastQuestion",index == questionsGlobal.getQuestions().size() - 1);

        return ResponseEntity.ok(returnedData);
    }

    // Read answer from client
    @CrossOrigin
    @RequestMapping(value = "/quiz/calculate", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Void> checkAnswer(@RequestBody AnswerDto answer) {

    // Check if selected answers equals correct answers - if yes increase user points - add yes/no to isCorrect array
    if (Arrays.equals(questionsGlobal.getQuestions().get(answer.getQuestionId()).getCorrect(), answer.getSelectedAnswers())){
        userPoints += questionsGlobal.getQuestions().get(answer.getQuestionId()).getPoints();
        isCorrect.add("Yes");
    }
    else isCorrect.add("No");

        // Array of user answers
        List<String> selectedAnswers = new ArrayList<>();
        for (int k = 0 ; k < answer.getSelectedAnswers().length ; k++)
            selectedAnswers.add(questionsGlobal.getQuestions().get(answer.getQuestionId()).getAnswers().get(answer.getSelectedAnswers()[k]-1));

        userAnswers.add(selectedAnswers);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Generate results
    @CrossOrigin
    @GetMapping("/quiz/report")
    public ResponseEntity<HashMap<String, Object>> getResults() {

        String result = userPoints >= (maxPoints / 2) ? "Passed" : "Failed" ;

        // Create returned JSON
        HashMap<String, Object> returnedData = new HashMap<>();
        returnedData.put("result",result);
        returnedData.put("userPoints",userPoints);
        returnedData.put("maxPoints",maxPoints);

        return ResponseEntity.ok(returnedData);
    }
}
