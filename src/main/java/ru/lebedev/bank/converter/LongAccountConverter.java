package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.accountPlan.AccountPlanDTO;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;

@Component
@RequiredArgsConstructor
public class LongAccountConverter implements Converter<String, AccountDTO> {
    private final AccountService accountService;

    @Override
    public AccountDTO convert(String source) {
        return accountService.findById(Long.parseLong(source)).orElseThrow();
    }


}
