package ru.lebedev.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.lebedev.bank.domain.accountPlan.AccountPlanDTO;
import ru.lebedev.bank.domain.user.UserDTO;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountDTO {

    private Long id;
    private BigDecimal amount;
    private Boolean isDefault;
    private Boolean isClosed;
    private UserDTO user;
    private AccountPlanDTO accountPlan;

}
