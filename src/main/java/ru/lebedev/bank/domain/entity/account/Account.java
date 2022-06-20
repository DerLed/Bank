package ru.lebedev.bank.domain.entity.account;

import java.math.BigDecimal;

public interface Account {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
}
