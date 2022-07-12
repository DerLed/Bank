package ru.lebedev.bank.exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException() {
        super();
    }

    public CardNotFoundException(Long id) {
        super("Could not find card id: " + id);
    }
}
