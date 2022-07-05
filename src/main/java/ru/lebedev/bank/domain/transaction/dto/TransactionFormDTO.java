package ru.lebedev.bank.domain.transaction.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionFormDTO {
    @Min(value = 6, message = "Номер телефона не может быть пустым")
    private String phoneNumber;
    @NotNull(message = "Номер телефона не может быть пустым")
    private BigDecimal amount;
    @NotNull(message = "Счет списания не выбран")
    private AccountDTO account;

}
