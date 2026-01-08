package com.quizzApp.quizz_service.feign;

import com.quizzApp.quizz_service.model.dto.QuestionAnswers;
import com.quizzApp.quizz_service.model.dto.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="QUESTION-SERVICE", path="/question") //Path works as prefix. You cant use @RequestMapping here as It's not controller
public interface QuestionServiceFeignInterface {
    @GetMapping("generate")
    ResponseEntity<List<Integer>> generateQuestions(
            @RequestParam String category,
            @RequestParam(defaultValue = "5") Integer noOfQues);

    @PostMapping("getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionByIds(
            @RequestBody List<Integer> questionIds);

    @PostMapping("queResponses")
    ResponseEntity<List<QuestionAnswers>> getQuestionAnswers(
            @RequestBody List<Integer> questionIds);
}
