package ru.lebedev.bank.formatter;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.account.AccountRepository;

import java.text.ParseException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AccountFormatter implements Formatter<Account> {
    private final AccountRepository accountRepository;

    @Override
    public Account parse(String text, Locale locale) throws ParseException {
        return accountRepository.findById(Long.parseLong(text)).orElseThrow();
    }

    @Override
    public String print(Account object, Locale locale) {
        return String.format("Наш объект %d", object.getId());
    }
}
