package ru.lebedev.bank.domain.account.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.card.dto.CardDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountCardTransferFormDTO {
    @NotNull(message = "Карта зачисления не выбрана")
    private CardDTO cardDTO;
    @NotNull(message = "Сумма не введена")
    private BigDecimal amount;
    @NotNull(message = "Счет списания не выбран")
    private CheckingAccountDTO accountDTO;

}
