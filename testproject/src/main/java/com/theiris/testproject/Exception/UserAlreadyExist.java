package com.theiris.testproject.Exception;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String msg){
        super(msg);
    }
}
