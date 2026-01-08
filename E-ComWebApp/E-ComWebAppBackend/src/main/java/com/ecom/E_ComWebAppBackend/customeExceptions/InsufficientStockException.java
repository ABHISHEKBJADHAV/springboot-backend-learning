package com.ecom.E_ComWebAppBackend.customeExceptions;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String msg)
    {
        super(msg);
    }
}
