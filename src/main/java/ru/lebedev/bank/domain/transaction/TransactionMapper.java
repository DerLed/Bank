package ru.lebedev.bank.domain.transaction;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.EntityMapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
