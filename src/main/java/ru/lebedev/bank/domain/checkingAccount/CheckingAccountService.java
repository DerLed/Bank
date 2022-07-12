package ru.lebedev.bank.domain.checkingAccount;

import ru.lebedev.bank.domain.helper.BaseService;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CheckingAccountService extends BaseService<CheckingAccountDTO, Long> {

    List<CheckingAccountDTO> findByClientLogin(String login);

    void addMoney(Long accountId, BigDecimal amount);

    List<CheckingAccountDTO> findByClientId (Long clientId);

    List<CheckingAccountDTO> findByPhoneNumber (String phoneNumber);

}
