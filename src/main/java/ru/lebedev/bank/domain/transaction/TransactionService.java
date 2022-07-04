package ru.lebedev.bank.domain.transaction;

import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findAll();

    TransactionDTO save(TransactionDTO transactionRequestDTO);

    List<TransactionDTO> findAllByTargetAccountId(Long id);

    List<TransactionDTO> findAllBySourceAccountId(Long id);

    void delete(TransactionDTO transactionDTO);
}
