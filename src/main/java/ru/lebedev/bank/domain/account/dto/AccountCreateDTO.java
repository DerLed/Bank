package ru.lebedev.bank.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.lebedev.bank.domain.account.dto.validGroup.LoanCreateInfo;
import ru.lebedev.bank.domain.account.dto.validGroup.SavingCreateInfo;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 */

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
