package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.account.checking.CheckingAccount;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.account.saving.SavingAccount;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SavingAccountMapper extends EntityMapper<SavingAccountDTO, SavingAccount> {

    @Override
    @Mapping(target = "client", source = "clientDTO")
    SavingAccount toEntity(SavingAccountDTO dto);

    @Override
    @InheritInverseConfiguration
    SavingAccountDTO toDTO(SavingAccount entity);
}
