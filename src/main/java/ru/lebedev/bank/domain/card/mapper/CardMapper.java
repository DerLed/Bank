package ru.lebedev.bank.domain.card.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.card.Card;
import ru.lebedev.bank.domain.card.dto.CardDTO;

@Mapper(componentModel = "spring")
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Mapping(target = "account", source = "accountDTO")
    @Mapping(target = "client", source = "clientDTO")
    @Mapping(target = "cardPlan", source = "cardPlanDTO")
    Card toEntity(CardDTO cardDTO);

    @InheritInverseConfiguration
    CardDTO toDTO(Card card);
}
