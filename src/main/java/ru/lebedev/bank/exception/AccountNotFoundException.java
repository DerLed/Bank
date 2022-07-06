package ru.lebedev.bank.exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException() {
        super();
    }


    public AccountNotFoundException(String message) {
        super(message);
    }
}
