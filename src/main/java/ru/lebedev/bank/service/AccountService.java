package ru.lebedev.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.entity.Account;
import ru.lebedev.bank.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public List<Account> findByPhoneNumber(String phoneNumber) {
        return accountRepository.findByClientPhoneNumber(phoneNumber);
    }
}
