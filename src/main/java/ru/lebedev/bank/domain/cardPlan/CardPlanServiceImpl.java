package ru.lebedev.bank.domain.cardPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardPlanServiceImpl implements CardPlanService{
    private final CardPlanRepository cardPlanRepository;

    private final CardPlanMapper cardPlanMapper;

    @Override
    public List<CardPlanDTO> findAll() {
        List<CardPlan> cardPlans = cardPlanRepository.findAll();
        return cardPlans.stream()
                .map(cardPlanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CardPlanDTO> findById(Long id) {
        Optional<CardPlan> cardPlan = cardPlanRepository.findById(id);
        return cardPlan.map(cardPlanMapper::toDTO);
    }

    @Override
    public CardPlanDTO save(CardPlanDTO cardPlanDTO) {
        CardPlan cardPlan = cardPlanMapper.toEntity(cardPlanDTO);

        cardPlanRepository.saveAndFlush(cardPlan);
        return cardPlanMapper.toDTO(cardPlan);
    }

    @Override
    public void deleteById(Long id) {
        cardPlanRepository.deleteById(id);
    }
}
