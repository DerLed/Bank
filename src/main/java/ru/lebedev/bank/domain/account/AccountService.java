package ru.lebedev.bank.domain.account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDTO> findAll();
    AccountDTO save(AccountDTO accountDTO);
    List<AccountDTO> findByClientId (Long clientId);
    List<AccountDTO> findByPhoneNumber (String phoneNumber);
    void deleteById(Long id);
}
