package com.example.QuizzAppMonolithic.service;

import com.example.QuizzAppMonolithic.dao.QuestionDao;
import com.example.QuizzAppMonolithic.exceptionHandling.ResourceNotFoundException;
import com.example.QuizzAppMonolithic.model.Question;
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
}
