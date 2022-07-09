package ru.lebedev.bank.domain.account.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.utills.DepositCalc;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {

    @Override
    @Mapping(target = "savingAmount", ignore = true)
    AccountDTO toDTO(Account entity);

    @AfterMapping // or @BeforeMapping
    default void calculateSavingAmount(Account account, @MappingTarget AccountDTO dto) {
//        if(account.getAccountPlan().getType().equals(TypeAccount.SAVING)) {
//            dto.setSavingAmount(DepositCalc.depositCalc(account));
//        }
    }

}
