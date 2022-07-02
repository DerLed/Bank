package ru.lebedev.bank.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.AccountDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
    private AccountDTO sourceAccount;
    private AccountDTO targetAccount;
    private TransactionStatus status;
}
