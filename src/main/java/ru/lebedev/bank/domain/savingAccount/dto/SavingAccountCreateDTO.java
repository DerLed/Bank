package ru.lebedev.bank.domain.savingAccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingAccountCreateDTO {
    //Валидация всех полей происходит в кастомном валидаторе
    private BigDecimal amount;

    private Long period;

    private AccountPlanDTO accountPlanDTO;

    private ClientDTO clientDTO;

    private CheckingAccountDTO checkingAccountDTO;
}
