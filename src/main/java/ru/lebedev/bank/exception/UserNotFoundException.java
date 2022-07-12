package ru.lebedev.bank.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Long id) {
        super("Could not find user id: " + id);
    }
}
