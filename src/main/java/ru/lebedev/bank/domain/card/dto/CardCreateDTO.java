package ru.lebedev.bank.domain.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;

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
