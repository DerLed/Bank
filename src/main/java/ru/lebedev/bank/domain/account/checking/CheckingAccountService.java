package ru.lebedev.bank.domain.account.checking;

import ru.lebedev.bank.domain.BaseService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CheckingAccountService extends BaseService<CheckingAccountDTO, Long> {

    List<CheckingAccountDTO> findByClientLogin(String login);

    void addMoney(Long accountId, BigDecimal amount);

//    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);


//
//    AccountDTO create(AccountCreateDTO accountCreateDTO);
//
//    Optional<AccountDTO> findById(Long id);
//    AccountDTO save(AccountDTO accountDTO);
//    void close(Long id);
//
    List<CheckingAccountDTO> findByClientId (Long clientId);
    List<CheckingAccountDTO> findByPhoneNumber (String phoneNumber);
//    Optional<AccountDTO> findByCardNumber (String cardNumber);
//
//    List<AccountDTO> findByClientLoginAndType(String login, TypeAccount type);
//    List<AccountDTO> findAllByClientLogin(String login);
//
//    List<TransactionDTO> getHistory (Long id);
//
//    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);
//    void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount);
//    void addMoney(Long accountId, BigDecimal amount);
}
