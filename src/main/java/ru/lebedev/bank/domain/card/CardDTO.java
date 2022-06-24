package ru.lebedev.bank.domain.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanDTO;
import ru.lebedev.bank.domain.user.UserDTO;

@Data
@AllArgsConstructor
public class CardDTO {

    private Long id;
    private AccountDTO accountDTO;
    private String pin;
    private UserDTO userDTO;
    private CardPlanDTO cardPlanDTO;
    private Boolean isClosed;
    private String cardNumber;
    private Boolean isBlocked;

}
