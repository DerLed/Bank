package ru.lebedev.bank.exception;

public class CloseDefaultAccountException extends RuntimeException{
    public CloseDefaultAccountException() {
        super();
    }


    public CloseDefaultAccountException(Long id) {
        super("This is default account id: " + id);
    }
}
