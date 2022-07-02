package ru.lebedev.bank.domain.transaction;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.AccountDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionFormDTO {

    private String phoneNumber;
    private BigDecimal amount;
    private AccountDTO account;

}
