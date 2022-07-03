package ru.lebedev.bank.domain.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanDTO;
import ru.lebedev.bank.domain.client.ClientDTO;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateDTO {

    @NotNull
    private AccountDTO accountDTO;
    @NotNull
    private CardPlanDTO cardPlanDTO;

}
