package ru.lebedev.bank.domain.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.helper.validGroup.Create;
import ru.lebedev.bank.domain.helper.validGroup.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private String cardNumber;

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Boolean isBlocked;

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Boolean isClosed;

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private AccountDTO accountDTO;

    @NotNull(groups = {Create.class, Update.class})
    private ClientDTO clientDTO;

    private String month;
    private String year;

}
