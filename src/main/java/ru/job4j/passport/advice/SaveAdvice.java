package ru.job4j.passport.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.passport.domain.ErrorResponse;

@ControllerAdvice
public class SaveAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException() {
        return new ResponseEntity<>(new ErrorResponse("A user with such a series and passport "
                + "number is already in the database."), HttpStatus.BAD_REQUEST);
    }

}