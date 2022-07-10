package ru.lebedev.bank.domain.transaction.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
