package ru.lebedev.bank.exception;

public class AccountTransferException extends RuntimeException {
    public AccountTransferException(String message) {
        super(message);
    }
}
