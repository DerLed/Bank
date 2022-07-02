package ru.lebedev.bank.domain.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanDTO;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.user.UserDTO;

@Data
@AllArgsConstructor
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
