package ru.lebedev.bank.domain.cardPlan.mapper;

import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.cardPlan.CardPlan;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;

@Mapper(componentModel = "spring")
public interface CardPlanMapper extends EntityMapper<CardPlanDTO, CardPlan> {
}
