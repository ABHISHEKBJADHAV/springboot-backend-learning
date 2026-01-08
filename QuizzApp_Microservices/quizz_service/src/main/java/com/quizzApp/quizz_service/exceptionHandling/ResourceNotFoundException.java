package com.quizzApp.quizz_service.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
