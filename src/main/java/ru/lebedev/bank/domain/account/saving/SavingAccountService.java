package ru.lebedev.bank.domain.account.saving;

import ru.lebedev.bank.domain.helper.BaseService;
import ru.lebedev.bank.domain.account.dto.SavingAccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;

import java.util.List;

public interface SavingAccountService extends BaseService<SavingAccountDTO, Long> {

//    List<CheckingAccountDTO> findAll();

    List<SavingAccountDTO> findByClientLogin(String login);

    SavingAccountDTO create(SavingAccountCreateDTO savingAccountCreateDTO);


//
//    AccountDTO create(AccountCreateDTO accountCreateDTO);
//
//    Optional<AccountDTO> findById(Long id);
//    AccountDTO save(AccountDTO accountDTO);
//    void close(Long id);
//
    List<SavingAccountDTO> findByClientId (Long clientId);
    List<SavingAccountDTO> findByPhoneNumber (String phoneNumber);
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
