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
public class AccountDTO {

    private Long id;

    @NotNull
    private BigDecimal amount;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dateOpened;
    private Long period;
    private Boolean isDefault;
    private Boolean isClosed;

    private String accountNumber;

    @NotNull
    private ClientDTO client;
    @NotNull
    private AccountPlanDTO accountPlan;

    private BigDecimal savingAmount;



}
