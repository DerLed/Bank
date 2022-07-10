package ru.lebedev.bank.formatter;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.checking.CheckingAccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;

import java.text.ParseException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CheckingAccountDTOFormatter implements Formatter<CheckingAccountDTO> {

    private final CheckingAccountService checkingAccountService;

    @Override
    public CheckingAccountDTO parse(String text, Locale locale) throws ParseException {
        return checkingAccountService.findById(Long.parseLong(text)).orElseThrow();
    }


    @Override
    public String print(CheckingAccountDTO object, Locale locale) {
        return String.format("Баланс: %s, Номер счета: %s", object.getAmount().toString(),
                object.getAccountNumber());
    }


}
