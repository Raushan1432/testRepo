package com.theiris.testproject.Exception;

import com.theiris.testproject.Payload.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorInfo> handleUserAlreadyExist(UserAlreadyExist e) {
        ErrorInfo err = new ErrorInfo(
                new Date(),
                e.getMessage(),
                "User already exists"
        );

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}
