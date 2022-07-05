package ru.lebedev.bank.domain.card.mapper;

import org.mapstruct.*;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.card.Card;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.utills.DepositCalc;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Mapping(target = "account", source = "accountDTO")
    @Mapping(target = "client", source = "clientDTO")
    @Mapping(target = "cardPlan", source = "cardPlanDTO")
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
