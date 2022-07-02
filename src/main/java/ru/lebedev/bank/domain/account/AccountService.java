package ru.lebedev.bank.domain.account;

import net.bytebuddy.dynamic.DynamicType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDTO> findAll();
    AccountDTO save(AccountDTO accountDTO);

    Optional<AccountDTO> findById(Long id);

    List<AccountDTO> findByClientId (Long clientId);
    List<AccountDTO> findByClientLogin (String login);

    List<AccountDTO> findByClientLoginLoanAccounts (String login);
    List<AccountDTO> findByClientLoginSavingAccounts(String login);
    List<AccountDTO> findByClientLoginCheckingAccounts (String login);

    List<AccountDTO> findByPhoneNumber (String phoneNumber);

    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);

    void deleteById(Long id);
}
