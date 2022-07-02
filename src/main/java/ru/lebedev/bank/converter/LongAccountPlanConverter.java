package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.accountPlan.AccountPlanDTO;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;

@Component
@RequiredArgsConstructor
public class LongAccountPlanConverter implements Converter<String, AccountPlanDTO> {
    private final AccountPlanService accountPlanService;

    @Override
    public AccountPlanDTO convert(String source) {
        return accountPlanService.findById(Long.parseLong(source)).orElseThrow();
    }

}
