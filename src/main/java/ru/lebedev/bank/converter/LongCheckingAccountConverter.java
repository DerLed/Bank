package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.checking.CheckingAccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;

@Component
@RequiredArgsConstructor
public class LongCheckingAccountConverter implements Converter<String, CheckingAccountDTO> {
    private final CheckingAccountService checkingAccountService;

    @Override
    public CheckingAccountDTO convert(String source) {
        return checkingAccountService.findById(Long.parseLong(source)).orElseThrow();
    }

}
