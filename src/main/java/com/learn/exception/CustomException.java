package com.learn.exception;

public class CustomException extends Exception{
    public CustomException(String message,Object object){
        super(message);
    }
}
