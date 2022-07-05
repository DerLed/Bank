package ru.lebedev.bank.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreateDTO {


    @NotNull
    private BigDecimal amount;

    @NotNull
    private AccountPlanDTO accountPlan;

    private ClientDTO client;

    private AccountDTO sourceAccount;

}
