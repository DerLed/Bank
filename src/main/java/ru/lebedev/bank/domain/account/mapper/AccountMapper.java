package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.account.dto.AccountDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {

}
