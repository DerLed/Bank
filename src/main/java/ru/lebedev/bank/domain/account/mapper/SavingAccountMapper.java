package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.account.saving.SavingAccount;
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

    @AfterMapping // or @BeforeMapping
    default void calculateSavingAmount(SavingAccount account, @MappingTarget SavingAccountDTO dto) {

//        if (! (account.getDateOpened() == null)) {
            dto.setSavingAmount(DepositCalc.depositCalc(account));
//        }
    }

}
