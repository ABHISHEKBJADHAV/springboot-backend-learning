package com.quizzApp.question_service.controller;

import com.quizzApp.question_service.model.Question;
import com.quizzApp.question_service.model.dto.QuestionAnswers;
import com.quizzApp.question_service.model.dto.QuestionWrapper;
import com.quizzApp.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(
            @RequestParam String category,
            @RequestParam(defaultValue = "5") Integer noOfQues)
    {
        return questionService.generateNoOfQuestionsByCategory(category,noOfQues);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByIds(
            @RequestBody List<Integer> questionIds)
    {
        return questionService.getQuestion(questionIds);
    }

    @PostMapping("queResponses")
    public ResponseEntity<List<QuestionAnswers>> getQuestionAnswers(
            @RequestBody List<Integer> questionIds)
    {
        return questionService.getAnswersOfQuestions(questionIds);
    }

}
