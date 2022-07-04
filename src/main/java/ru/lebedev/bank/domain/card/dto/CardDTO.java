package ru.lebedev.bank.domain.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {

    private Long id;
    private String cardNumber;
    private String pin;
    private Boolean isBlocked;
    private Boolean isClosed;

    private AccountDTO accountDTO;
    private ClientDTO clientDTO;
    private CardPlanDTO cardPlanDTO;

}
