package ru.lebedev.bank.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingAccountCreateDTO {

    private BigDecimal amount;

    private Long period;

    private AccountPlanDTO accountPlanDTO;

    private ClientDTO clientDTO;

    private CheckingAccountDTO checkingAccountDTO;
}
