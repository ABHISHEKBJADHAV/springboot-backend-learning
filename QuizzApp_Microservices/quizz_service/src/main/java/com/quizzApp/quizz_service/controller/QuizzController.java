package com.quizzApp.quizz_service.controller;

import com.quizzApp.quizz_service.model.Quizz;
import com.quizzApp.quizz_service.model.dto.QuestionResponse;
import com.quizzApp.quizz_service.model.dto.QuestionWrapper;
import com.quizzApp.quizz_service.model.dto.QuizzRequestDto;
import com.quizzApp.quizz_service.service.QuizzService;
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
            @RequestBody QuizzRequestDto quizzReqDto)
    {
        return quizzService.createQuizz(
                quizzReqDto.category(),
                quizzReqDto.noOfQues(),
                quizzReqDto.quizzName()
        );
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizzQuestions(@PathVariable int id)
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
