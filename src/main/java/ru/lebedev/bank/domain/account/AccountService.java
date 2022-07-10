package ru.lebedev.bank.domain.account;


import ru.lebedev.bank.domain.account.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<AccountDTO> findById(Long id);

    AccountDTO save(AccountDTO accountDTO);

    List<AccountDTO> findAllByClientLogin(String login);

    void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount);

    void transferMoney(BigDecimal amount, Long accountId, Account accountTarget);

    void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount);

}
