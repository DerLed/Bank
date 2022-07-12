package ru.lebedev.bank.domain.checkingAccount.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.checkingAccount.CheckingAccount;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CheckingAccountMapper extends EntityMapper<CheckingAccountDTO, CheckingAccount> {

    @Override
    @Mapping(target = "client", source = "clientDTO")
    CheckingAccount toEntity(CheckingAccountDTO dto);

    @Override
    @InheritInverseConfiguration
    CheckingAccountDTO toDTO(CheckingAccount entity);
}
