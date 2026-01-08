package com.ecom.E_ComWebAppBackend.customeExceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg)
    {
        super(msg);
    }
}

