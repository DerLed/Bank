package ru.lebedev.bank.domain.savingAccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.helper.validGroup.Create;
import ru.lebedev.bank.domain.helper.validGroup.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingAccountDTO {
    @Null(groups = {Create.class, Update.class})
    private Long id;


    @NotNull(message = "Поле не может быть пустым", groups = {Update.class, Create.class})
    @PositiveOrZero(message = "Сумма должна быть не меньше 0", groups = Update.class)
    private BigDecimal amount;

    @Null(groups = Create.class)
    private LocalDateTime dateOpened;

    @NotNull(message = "Поле период не может быть пустым", groups = {Update.class, Create.class})
    private Long period;

    @NotNull(message = "Не выбран аккаунт", groups = {Update.class, Create.class})
    private AccountPlanDTO accountPlanDTO;

    @Null(groups = Create.class)
    @NotNull(message = "Поле не может быть пустым", groups = Update.class)
    private Boolean isClosed;

    @Null(groups = Create.class)
    @NotBlank(message = "Номер счета не может быть пустым", groups = Update.class)
    private String accountNumber;

    @NotNull(message = "Не выбран клиент", groups = {Update.class, Create.class})
    private ClientDTO clientDTO;

    @Null(groups = Create.class)
    @NotNull(message = "Поле не может быть пустым", groups = Update.class)
    private Boolean isDefault;

    @Null(groups = {Update.class, Create.class})
    private BigDecimal savingAmount;

}
