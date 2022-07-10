package ru.lebedev.bank.domain.card;

import ru.lebedev.bank.domain.card.dto.CardCreateDTO;
import ru.lebedev.bank.domain.card.dto.CardDTO;

import java.util.List;
import java.util.Optional;

public interface CardService {
    CardDTO create(CardCreateDTO cardCreateDTO);
//
//    void closeById(Long id);
//
//    void closeByAccountId(Long accountId);
//
//    void blockById(Long id);
//
//    List<CardDTO> findByClientId(Long userId);
//    List<CardDTO> findByClientId(Long userId);
//
    List<CardDTO> findByClientLogin(String login);
//
    Optional<CardDTO> findById(Long id);
//
//    Optional<CardDTO> findByCardNumber(String cardNumber);
}
