package ru.lebedev.bank.domain.transaction.dto;

import lombok.*;
import ru.lebedev.bank.domain.transaction.TransactionStatus;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
    @EqualsAndHashCode.Exclude
    private AccountDTO sourceAccount;
    @EqualsAndHashCode.Exclude
    private AccountDTO targetAccount;
    private TransactionStatus status;
}
