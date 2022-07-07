package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.utills.DepositCalc;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AccountCreateMapper extends EntityMapper<AccountCreateDTO, Account> {
    @Override
    @Mapping(target = "amount", ignore = true)
    Account toEntity(AccountCreateDTO dto);

    @Override
    @InheritInverseConfiguration
    AccountCreateDTO toDTO(Account entity);
}
