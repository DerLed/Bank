package ru.lebedev.bank.domain.accountPlan;

import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.EntityMapper;

@Mapper(componentModel = "spring")
public interface AccountPlanMapper extends EntityMapper<AccountPlanDTO, AccountPlan> {
}
