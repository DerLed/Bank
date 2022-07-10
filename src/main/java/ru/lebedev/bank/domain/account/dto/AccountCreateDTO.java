package ru.lebedev.bank.domain.account.dto;


import lombok.*;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AccountCreateDTO {


    private BigDecimal amount;

    private AccountPlanDTO accountPlan;

    //Срок в месяцах
    private Long period;

    private AccountDTO sourceAccount;

    private ClientDTO client;

}
