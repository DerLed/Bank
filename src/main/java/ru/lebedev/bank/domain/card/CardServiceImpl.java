package ru.lebedev.bank.domain.card;

import java.util.List;
import java.util.Optional;

public class CardServiceImpl implements CardService{
    @Override
    public CardDTO save(CardDTO cardDTO) {
        return null;
    }

    @Override
    public void closeById(Long id) {

    }

    @Override
    public void closeByAccountId(Long accountId) {

    }

    @Override
    public void blockById(Long id) {

    }

    @Override
    public List<CardDTO> findByUserId(Long userId) {
        return null;
    }

    @Override
    public Optional<CardDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CardDTO> findByCardNumber(String cardNumber) {
        return Optional.empty();
    }
}
