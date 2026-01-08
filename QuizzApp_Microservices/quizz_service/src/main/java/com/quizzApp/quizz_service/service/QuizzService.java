package com.quizzApp.quizz_service.service;

import com.quizzApp.quizz_service.dao.QuizzDao;
import com.quizzApp.quizz_service.exceptionHandling.ResourceNotFoundException;
import com.quizzApp.quizz_service.feign.QuestionServiceFeignInterface;
import com.quizzApp.quizz_service.model.Quizz;
import com.quizzApp.quizz_service.model.dto.QuestionAnswers;
import com.quizzApp.quizz_service.model.dto.QuestionResponse;
import com.quizzApp.quizz_service.model.dto.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizzService {

    @Autowired
    private QuizzDao quizzDao;
    @Autowired
    private QuestionServiceFeignInterface questionService;

    public ResponseEntity<Quizz> createQuizz(String category, Integer noOfQues, String quizzName) {
        if(quizzName==null)
        {
            quizzName=category+"Quizz";
        }
        List<Integer> questionIds = questionService.generateQuestions(category,noOfQues).getBody();
        Quizz quizz = new Quizz();
        quizz.setQuizzTitle(quizzName);
        quizz.setQuestionIds(questionIds);
        quizzDao.save(quizz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizzQuestions(int id) {
        Optional<Quizz> quizz = quizzDao.findById(id);
        if(quizz.isEmpty())
        {
            throw new ResourceNotFoundException("Quizz with id: "+id+" not found");
        }
        List<Integer> questionIds = quizz.get().getQuestionIds();
        List<QuestionWrapper> quizzQuestions = questionService.getQuestionByIds(questionIds).getBody();
        return ResponseEntity.ok(quizzQuestions);
    }

    public ResponseEntity<Integer> submitQuizzAndgetResult(List<QuestionResponse> questionResponses, int id) {
        Optional<Quizz> quizz = quizzDao.findById(id);
        if(quizz.isEmpty())
        {
            throw new ResourceNotFoundException("Quizz with id: "+id+" not found while checking results");
        }
        int score=0;
        List<Integer> questionIds = quizz.get().getQuestionIds();
        List<QuestionAnswers> questionAnswers= questionService.getQuestionAnswers(questionIds).getBody();
        questionResponses.sort(Comparator.comparingInt(QuestionResponse::id));
        questionAnswers.sort(Comparator.comparingInt(QuestionAnswers::id));
        for(int i=0;i<questionResponses.size();i++)
        {
            QuestionResponse queResponse = questionResponses.get(i);
            if(queResponse.response().equals(questionAnswers.get(i).correctAnswer()))
                score++;
        }
        return ResponseEntity.ok(score);
    }
}
