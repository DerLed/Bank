package ru.lebedev.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.AccountPlanDTO;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.user.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime dateOpened;
    private Boolean isDefault;
    private Boolean isClosed;
    private ClientDTO client;
    private AccountPlanDTO accountPlan;

}
