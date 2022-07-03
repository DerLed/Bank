package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import net.andreinc.mockneat.MockNeat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.types.enums.CreditCardType.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Override
    public CardDTO save(CardDTO cardRequestDTO) {
        Card entity = cardMapper.toEntity(cardRequestDTO);
        if (entity.getCardNumber() == null) {
            entity.setCardNumber(getNewCardNumber());
        }
        cardRepository.saveAndFlush(entity);
        return cardMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public void closeById(Long id) {
        cardRepository.softDeleteById(id);
    }

    @Override
    @Transactional
    public void closeByAccountId(Long accountId) {
        cardRepository.softDeleteAllByAccountId(accountId);
    }

    @Override
    @Transactional
    public void blockById(Long id) {
        cardRepository.blockById(id);
    }

    @Override
    public List<CardDTO> findByClientId(Long userId) {
        List<Card> cards = cardRepository.findByClientIdAndIsClosedFalse(userId);
        return cards.stream()
                .map(cardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> findByClientUserLogin(String login) {
        return cardRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(cardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CardDTO> findById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.map(cardMapper::toDTO);
    }

    @Override
    public Optional<CardDTO> findByCardNumber(String cardNumber) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        return card.map(cardMapper::toDTO);
    }

    private String getNewCardNumber() {
        String cardNumber;
        do {
            cardNumber = MockNeat.old().creditCards().types(VISA_16, MASTERCARD, CHINA_UNION_PAY_16).get();
        } while (cardRepository.findByCardNumber(cardNumber).isPresent());

        return cardNumber;
    }
}
