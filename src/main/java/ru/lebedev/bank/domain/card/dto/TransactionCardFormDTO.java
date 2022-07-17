package ru.lebedev.bank.domain.card.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
//Валидация полей в кастомном валидаторе
public class TransactionCardFormDTO {

    private String cardNumber;

    private BigDecimal amount;

    private CardDTO cardDTO;

}
