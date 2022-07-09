package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.account.checking.CheckingAccount;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CheckingAccountMapper extends EntityMapper<CheckingAccountDTO, CheckingAccount> {

    @Override
    @Mapping(target = "client", source = "clientDTO")
    CheckingAccount toEntity(CheckingAccountDTO dto);

    @Override
    @InheritInverseConfiguration
    CheckingAccountDTO toDTO(CheckingAccount entity);
}
