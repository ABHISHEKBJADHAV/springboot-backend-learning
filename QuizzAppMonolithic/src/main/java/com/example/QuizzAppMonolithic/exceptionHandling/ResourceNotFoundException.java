package com.example.QuizzAppMonolithic.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
