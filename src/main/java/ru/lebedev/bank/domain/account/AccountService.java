package ru.lebedev.bank.domain.account;

import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountDTO> findAll();
    Optional<AccountDTO> findById(Long id);
    AccountDTO save(AccountDTO accountDTO);
    void close(Long id);

    List<AccountDTO> findByClientId (Long clientId);
    List<AccountDTO> findByPhoneNumber (String phoneNumber);
    Optional<AccountDTO> findByCardNumber (String cardNumber);

    List<AccountDTO> findByClientLogin (String login);
    List<AccountDTO> findByClientLoginLoanAccounts (String login);
    List<AccountDTO> findByClientLoginSavingAccounts(String login);
    List<AccountDTO> findByClientLoginCheckingAccounts (String login);

    List<TransactionDTO> getHistory (Long id);

    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);
    void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount);
    void addMoney(Long accountId, BigDecimal amount);
}
