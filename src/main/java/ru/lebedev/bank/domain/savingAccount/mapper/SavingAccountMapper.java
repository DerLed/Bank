package ru.lebedev.bank.domain.savingAccount.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.savingAccount.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.savingAccount.SavingAccount;
import ru.lebedev.bank.utills.DepositCalc;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SavingAccountMapper extends EntityMapper<SavingAccountDTO, SavingAccount> {

    @Override
    @Mapping(target = "client", source = "clientDTO")
    @Mapping(target = "accountPlan", source = "accountPlanDTO")
    @Mapping(target = "client.user", source = "clientDTO.userDTO")
    SavingAccount toEntity(SavingAccountDTO dto);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "savingAmount", ignore = true)
    SavingAccountDTO toDTO(SavingAccount entity);

    //Производится вычисление поля накопленной суммы
    @AfterMapping
    default void calculateSavingAmount(SavingAccount account, @MappingTarget SavingAccountDTO dto) {
        dto.setSavingAmount(DepositCalc.depositCalc(account));
    }

}
