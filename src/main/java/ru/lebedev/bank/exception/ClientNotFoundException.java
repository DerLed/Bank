package ru.lebedev.bank.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(Long id) {
        super("Could not find client id: " + id);
    }
}
