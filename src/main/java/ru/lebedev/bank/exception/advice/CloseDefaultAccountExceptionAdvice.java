package ru.lebedev.bank.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.lebedev.bank.exception.CloseDefaultAccountException;


@ControllerAdvice
public class CloseDefaultAccountExceptionAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CloseDefaultAccountException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    String closeDefaultAccountHandler(CloseDefaultAccountException ex) {
        return ex.getMessage();
    }

}
