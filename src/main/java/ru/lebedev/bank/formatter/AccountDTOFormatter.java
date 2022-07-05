package ru.lebedev.bank.formatter;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;

import java.text.ParseException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AccountDTOFormatter implements Formatter<AccountDTO> {

    private final AccountService accountService;

    @Override
    public AccountDTO parse(String text, Locale locale) throws ParseException {
        return accountService.findById(Long.parseLong(text)).orElseThrow();
    }



    @Override
    public String print(AccountDTO object, Locale locale) {
        return String.format("Баланс: %s, Тип: %s, Номер счета: %s", object.getAmount().toString(),
                object.getAccountPlan().getClass(),
                object.getAccountNumber());
    }
}
