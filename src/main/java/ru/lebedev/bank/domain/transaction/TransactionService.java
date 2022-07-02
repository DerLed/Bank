package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionDTO create(TransactionDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);

        transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);
    }
}
