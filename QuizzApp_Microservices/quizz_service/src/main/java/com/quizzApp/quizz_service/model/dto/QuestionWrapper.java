package com.quizzApp.quizz_service.model.dto;

public record QuestionWrapper(
        int id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4) { }
