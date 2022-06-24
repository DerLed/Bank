package ru.lebedev.bank.domain.transaction;

import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.AccountDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private AccountDTO sourceAccount;
    private AccountDTO targetAccount;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionStatus state;
}
