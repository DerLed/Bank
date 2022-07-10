package ru.lebedev.bank.domain.accountPlan.mapper;

import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;

@Mapper(componentModel = "spring")
public interface AccountPlanMapper extends EntityMapper<AccountPlanDTO, AccountPlan> {

}
