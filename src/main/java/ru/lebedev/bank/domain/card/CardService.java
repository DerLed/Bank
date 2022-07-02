package ru.lebedev.bank.domain.card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    CardDTO save(CardDTO cardDTO);

    void closeById(Long id);

    void closeByAccountId(Long accountId);

    void blockById(Long id);

    List<CardDTO> findByUserId(Long userId);

    Optional<CardDTO> findById(Long id);

    Optional<CardDTO> findByCardNumber(String cardNumber);
}
