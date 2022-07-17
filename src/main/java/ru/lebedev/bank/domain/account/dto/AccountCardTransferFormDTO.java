package ru.lebedev.bank.domain.account.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountCardTransferFormDTO {

    private CardDTO cardDTO;

    private BigDecimal amount;

    private CheckingAccountDTO accountDTO;

}
