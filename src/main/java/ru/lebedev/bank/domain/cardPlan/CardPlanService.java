package ru.lebedev.bank.domain.cardPlan;

import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;

import java.util.List;
import java.util.Optional;

public interface CardPlanService {
    List<CardPlanDTO> findAll();
    Optional<CardPlanDTO> findById(Long id);
    CardPlanDTO save(CardPlanDTO cardPlanDTO);
    void deleteById(Long id);
}
