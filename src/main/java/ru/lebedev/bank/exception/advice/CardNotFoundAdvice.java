package ru.lebedev.bank.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.lebedev.bank.exception.CardNotFoundException;


@ControllerAdvice
public class CardNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String accountNotFoundHandler(CardNotFoundException ex) {
        return ex.getMessage();
    }
}
