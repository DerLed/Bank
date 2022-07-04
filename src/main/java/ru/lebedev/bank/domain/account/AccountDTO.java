package ru.lebedev.bank.domain.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.AccountPlanDTO;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.user.UserDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private Long id;

    @NotNull
    private BigDecimal amount;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dateOpened;
    private Boolean isDefault;
    private Boolean isClosed;
   // private String accountNumber;
    private ClientDTO client;
    private AccountPlanDTO accountPlan;

}
