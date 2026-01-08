package com.example.QuizzAppMonolithic.controller;

import com.example.QuizzAppMonolithic.model.Quizz;
import com.example.QuizzAppMonolithic.model.dto.QuestionResponse;
import com.example.QuizzAppMonolithic.model.dto.QuizzQuestion;
import com.example.QuizzAppMonolithic.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quizz")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    @PostMapping("create")
    public ResponseEntity<Quizz> createQuizz(
            @RequestParam(defaultValue = "java") String category,
            @RequestParam(required = false, defaultValue = "5") int noOfQues,
            @RequestParam(required = false) String quizzName)
    {
        return quizzService.createQuizz(category,noOfQues,quizzName);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizzQuestion>> getQuizzQuestions(@PathVariable int id)
    {
        return quizzService.getQuizzQuestions(id);
    }


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getResult(
            @RequestBody List<QuestionResponse> questionResponses,
            @PathVariable int id
    )
    {
        return quizzService.submitQuizzAndgetResult(questionResponses,id);
    }

}
