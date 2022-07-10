package ru.lebedev.bank.domain.account;

import ru.lebedev.bank.aop.SendMessageTransaction;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

//    List<AccountDTO> findAll();
//
//    AccountDTO create(AccountCreateDTO accountCreateDTO);
//
    Optional<AccountDTO> findById(Long id);
    AccountDTO save(AccountDTO accountDTO);
//    void close(Long id);
//
//    List<AccountDTO> findByClientId (Long clientId);
//    List<AccountDTO> findByPhoneNumber (String phoneNumber);
//    Optional<AccountDTO> findByCardNumber (String cardNumber);
//
//    List<AccountDTO> findByClientLoginAndType(String login, TypeAccount type);
    List<AccountDTO> findAllByClientLogin(String login);

//
//    List<TransactionDTO> getHistory (Long id);
//
    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);


    void transferMoney(BigDecimal amount, Long accountId, Account accountTarget);
    void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount);
//    void addMoney(Long accountId, BigDecimal amount);
}
