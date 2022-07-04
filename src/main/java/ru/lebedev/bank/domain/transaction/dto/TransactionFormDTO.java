package ru.lebedev.bank.domain.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionFormDTO {

    private String phoneNumber;
    private BigDecimal amount;
    private AccountDTO account;

}
