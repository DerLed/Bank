package ru.lebedev.bank.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(Long id) {
        super("Could not find account id: " + id);
    }
}
