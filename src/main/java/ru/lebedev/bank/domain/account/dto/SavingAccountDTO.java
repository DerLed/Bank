package ru.lebedev.bank.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingAccountDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime dateOpened;

    private Long period;

    private AccountPlanDTO accountPlanDTO;

    private Boolean isClosed;

    private String accountNumber;

    private ClientDTO clientDTO;

    private Boolean isDefault;
}
