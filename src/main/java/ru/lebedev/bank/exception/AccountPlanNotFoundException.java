package ru.lebedev.bank.exception;

public class AccountPlanNotFoundException extends RuntimeException{
    public AccountPlanNotFoundException() {
        super();
    }

    public AccountPlanNotFoundException(Long id) {
        super("Could not find account plan id: " + id);
    }
}
