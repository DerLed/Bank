package ru.lebedev.bank.domain.checkingAccount.dto;

import lombok.*;
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
public class CheckingAccountDTO {
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @Null(groups = Create.class)
    @NotNull(message = "Поле не может быть пустым", groups = Update.class)
    @PositiveOrZero(message = "Сумма должна быть не меньше 0", groups = Update.class)
    private BigDecimal amount;

    @Null(groups = Create.class)
    private LocalDateTime dateOpened;

    @Null(groups = Create.class)
    @NotNull(message = "Поле не может быть пустым", groups = Update.class)
    private Boolean isClosed;

    @Null(groups = Create.class)
    @NotBlank(message = "Номер счета не может быть пустым", groups = Update.class)
    private String accountNumber;

    @NotNull(message = "Поле не может быть пустым", groups = {Update.class, Create.class})
    private ClientDTO clientDTO;

    @Null(groups = Create.class)
    @NotNull(message = "Поле не может быть пустым", groups = Update.class)
    private Boolean isDefault;
}
