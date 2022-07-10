package ru.lebedev.bank.domain.account.saving;

import ru.lebedev.bank.domain.helper.BaseService;
import ru.lebedev.bank.domain.account.dto.SavingAccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;

import java.util.List;

public interface SavingAccountService extends BaseService<SavingAccountDTO, Long> {

    List<SavingAccountDTO> findByClientLogin(String login);

    SavingAccountDTO create(SavingAccountCreateDTO savingAccountCreateDTO);

    List<SavingAccountDTO> findByClientId (Long clientId);

    List<SavingAccountDTO> findByPhoneNumber (String phoneNumber);

}
