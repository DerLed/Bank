package ru.lebedev.bank.domain.cardPlan;

import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.EntityMapper;

@Mapper(componentModel = "spring")
public interface CardPlanMapper extends EntityMapper<CardPlanDTO, CardPlan> {
}
