package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;

@Component
@RequiredArgsConstructor
public class LongAccountConverter implements Converter<String, AccountDTO> {
    private final AccountService accountService;

    @Override
    public AccountDTO convert(String source) {
        return accountService.findById(Long.parseLong(source)).orElseThrow();
    }


}
