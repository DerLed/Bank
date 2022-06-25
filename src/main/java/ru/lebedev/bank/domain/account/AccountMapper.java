package ru.lebedev.bank.domain.account;

import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.EntityMapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {

}
