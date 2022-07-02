package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionService {

    TransactionDTO create(TransactionDTO transactionRequestDTO);
    TransactionDTO save(TransactionDTO transactionRequestDTO);
    List<TransactionDTO> findAllByTargetAccountId(Long id);

    List<TransactionDTO> findAllBySourceAccountId(Long id);
}
