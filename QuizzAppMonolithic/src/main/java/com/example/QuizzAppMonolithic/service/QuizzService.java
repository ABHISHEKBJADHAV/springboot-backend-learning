package com.example.QuizzAppMonolithic.service;

import com.example.QuizzAppMonolithic.dao.QuestionDao;
import com.example.QuizzAppMonolithic.dao.QuizzDao;
import com.example.QuizzAppMonolithic.exceptionHandling.ResourceNotFoundException;
import com.example.QuizzAppMonolithic.model.Question;
import com.example.QuizzAppMonolithic.model.Quizz;
import com.example.QuizzAppMonolithic.model.dto.QuestionResponse;
import com.example.QuizzAppMonolithic.model.dto.QuizzQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizzService {

    @Autowired
    private QuizzDao quizzDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<Quizz> createQuizz(String category, int noOfQues, String quizzName) {
        if(quizzName==null)
        {
            quizzName=category+"Quizz";
        }
        List<Question> questions = questionDao.findNoOfRandomQuesByCategory(category,noOfQues);
        Quizz quizz = new Quizz();
        quizz.setQuizzTitle(quizzName);
        quizz.setQuestions(questions);
        quizzDao.save(quizz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizz);
    }

    public ResponseEntity<List<QuizzQuestion>> getQuizzQuestions(int id) {
        Optional<Quizz> quizz = quizzDao.findById(id);
        if(quizz.isEmpty())
        {
            throw new ResourceNotFoundException("Quizz with id: "+id+" not found");
        }
        List<Question> questions = quizz.get().getQuestions();
        List<QuizzQuestion> quizzQuestions = new ArrayList<>();
        for(int i=0;i<questions.size();i++)
        {
            Question question = questions.get(i);
            QuizzQuestion quizzQuestion = new QuizzQuestion(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
                    );
            quizzQuestions.add(quizzQuestion);
        }
        return ResponseEntity.ok(quizzQuestions);
    }

    public ResponseEntity<Integer> submitQuizzAndgetResult(List<QuestionResponse> questionResponses, int id) {
        Optional<Quizz> quizz = quizzDao.findById(id);
        if(quizz.isEmpty())
        {
            throw new ResourceNotFoundException("Quizz with id: "+id+" not found while checking results");
        }
        int score=0;
        List<Question> questions = quizz.get().getQuestions();
        for(int i=0;i<questionResponses.size();i++)
        {
            QuestionResponse queResponse = questionResponses.get(i);
            if(queResponse.response().equals(questions.get(i).getCorrectAnswer()))
            {
                score++;
            }
        }
        return ResponseEntity.ok(score);
    }
}
