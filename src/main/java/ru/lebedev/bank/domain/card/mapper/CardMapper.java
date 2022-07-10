package ru.lebedev.bank.domain.card.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.card.Card;
import ru.lebedev.bank.domain.card.dto.CardDTO;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Mapping(target = "account", source = "accountDTO")
    @Mapping(target = "client", source = "clientDTO")
    Card toEntity(CardDTO cardDTO);

    @InheritInverseConfiguration
    @Mapping(target = "month", ignore = true)
    @Mapping(target = "year", ignore = true)
    @Override
    CardDTO toDTO(Card entity);



    @AfterMapping
    default void calculateCardDate(Card card, @MappingTarget CardDTO cardDTO) {
        LocalDateTime dateOpened = card.getAccount().getDateOpened().plusYears(3L);
            cardDTO.setMonth(String.valueOf(dateOpened.getMonth().getValue()));
            cardDTO.setYear(String.valueOf(dateOpened.getYear()));

    }
}
