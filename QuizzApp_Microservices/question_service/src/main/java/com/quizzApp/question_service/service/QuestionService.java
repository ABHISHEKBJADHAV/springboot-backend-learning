package com.quizzApp.question_service.service;

import com.quizzApp.question_service.dao.QuestionDao;
import com.quizzApp.question_service.exceptionHandling.ResourceNotFoundException;
import com.quizzApp.question_service.model.Question;
import com.quizzApp.question_service.model.dto.QuestionAnswers;
import com.quizzApp.question_service.model.dto.QuestionWrapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questions = questionDao.findAll();
        if(questions.isEmpty())
        {
            throw new ResourceNotFoundException("No questions found");
        }
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> questions = questionDao.findAllByCategory(category);
        if(questions.isEmpty())
        {
            throw new ResourceNotFoundException("No questions found");
        }
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        Question savedQuestion = questionDao.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    public ResponseEntity<List<Integer>> generateNoOfQuestionsByCategory(String category, Integer noOfQues) {
        List<Integer> questionIds = questionDao.findNoOfRandomQuesByCategory(category, noOfQues);
        if(questionIds.isEmpty())
        {
            throw new ResourceNotFoundException("No questions found with category "+category);
        }
        return ResponseEntity.ok(questionIds);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestion(List<Integer> queIds) {
        List<QuestionWrapper> wrappedQuestions = new ArrayList<>();
        for(Integer queId:queIds) {
            Question question = questionDao.findById(queId).orElseThrow(
                    () -> new ResourceNotFoundException("Question not found with id: " + queId)
            );
            QuestionWrapper wrappedQuestion = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());
            wrappedQuestions.add(wrappedQuestion);
        }
        return ResponseEntity.ok(wrappedQuestions);
    }

    public ResponseEntity<@NonNull List<QuestionAnswers>> getAnswersOfQuestions(List<Integer> questionIds) {
        List<QuestionAnswers> questionAnswers = new ArrayList<>();
        for(Integer queId:questionIds)
        {
            Question question = questionDao.findById(queId).orElseThrow(
                    () -> new ResourceNotFoundException("Question not found with id: " + queId)
            );
            QuestionAnswers queAns = new QuestionAnswers(question.getId(),question.getCorrectAnswer());
            questionAnswers.add(queAns);
        }
        return ResponseEntity.ok(questionAnswers);
    }
}
