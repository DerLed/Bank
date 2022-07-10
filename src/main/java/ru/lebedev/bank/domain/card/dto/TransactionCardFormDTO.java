package ru.lebedev.bank.domain.card.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionCardFormDTO {
    @Min(value = 16, message = "Номер карты не может быть пустым")
    private String cardNumber;
    @NotNull(message = "Сумма перевода не может быть пустой")
    private BigDecimal amount;
    @NotNull(message = "Карта списания не выбрана")
    private CardDTO cardDTO;

}
