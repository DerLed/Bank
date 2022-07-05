package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.accountPlan.AccountPlanRepository;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanRepository;
import ru.lebedev.bank.domain.cardPlan.CardPlanService;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;

@Component
@RequiredArgsConstructor
public class LongCardPlanConverter implements Converter<String, CardPlanDTO> {
    private final CardPlanService cardPlanService;

    @Override
    public CardPlanDTO convert(String source) {
        return cardPlanService.findById(Long.parseLong(source)).orElseThrow();
    }

}
