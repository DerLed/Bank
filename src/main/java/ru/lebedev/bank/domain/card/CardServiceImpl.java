package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import net.andreinc.mockneat.MockNeat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.card.dto.CardCreateDTO;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.card.mapper.CardMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.types.enums.CreditCardType.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final AccountService accountService;
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
    public CardDTO updateById(Long id, CardDTO dto) {
        return cardRepository.findById(id)
                .map(account -> {
                    BeanUtils.copyProperties(cardMapper.toEntity(dto), account, "id");
                    return cardMapper.toDTO(cardRepository.save(account));
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    return cardMapper.toDTO(cardRepository.save(cardMapper.toEntity(dto)));
                });
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public CardDTO create(CardCreateDTO cardRequestDTO) {

        AccountDTO accountDTO = AccountDTO.builder()
                .client(cardRequestDTO.getClientDTO())
                .build();

        AccountDTO savedAccountDTO = accountService.save(accountDTO);

        CardDTO cardDTO = CardDTO.builder()
                .clientDTO(cardRequestDTO.getClientDTO())
                .accountDTO(savedAccountDTO)
                .cardNumber(getNewCardNumber())
                .build();

        Card card = cardRepository.saveAndFlush(cardMapper.toEntity(cardDTO));
        return  cardMapper.toDTO(card);
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
    public List<CardDTO> findByClientLogin(String login) {
        return cardRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(cardMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> findAll() {
        return cardRepository.findAll().stream().map(cardMapper::toDTO).collect(Collectors.toList());
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

    @Override
    public boolean existsByCardNumber(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    private String getNewCardNumber() {
        String cardNumber;
        do {
            cardNumber = MockNeat.old().creditCards().types(VISA_16, MASTERCARD, CHINA_UNION_PAY_16).get();
        } while (cardRepository.findByCardNumber(cardNumber).isPresent());

        return cardNumber;
    }
}
