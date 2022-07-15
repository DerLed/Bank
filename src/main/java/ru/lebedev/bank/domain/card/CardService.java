package ru.lebedev.bank.domain.card;

import ru.lebedev.bank.domain.card.dto.CardCreateDTO;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.helper.BaseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface CardService extends BaseService<CardDTO, Long> {

    CardDTO create(CardCreateDTO cardCreateDTO);

    void closeById(Long id);

    void closeByAccountId(Long accountId);

    void blockById(Long id);

    List<CardDTO> findByClientId(Long userId);

    List<CardDTO> findByClientLogin(String login);

    Optional<CardDTO> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
}
