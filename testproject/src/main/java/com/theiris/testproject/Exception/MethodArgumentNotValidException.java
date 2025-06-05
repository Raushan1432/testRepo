package com.theiris.testproject.Exception;


public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String msg){
        super(msg);
    }
}
