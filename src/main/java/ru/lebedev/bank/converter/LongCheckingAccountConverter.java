package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.checkingAccount.CheckingAccountService;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;

@Component
@RequiredArgsConstructor
public class LongCheckingAccountConverter implements Converter<String, CheckingAccountDTO> {
    private final CheckingAccountService checkingAccountService;

    @Override
    public CheckingAccountDTO convert(String source) {
        return checkingAccountService.findById(Long.parseLong(source)).orElseThrow();
    }

}
