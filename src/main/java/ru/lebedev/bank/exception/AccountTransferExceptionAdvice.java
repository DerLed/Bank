package ru.lebedev.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountTransferExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(AccountTransferException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String accountTransferExceptionHandler(AccountTransferException ex) {
        return ex.getMessage();
    }
}
