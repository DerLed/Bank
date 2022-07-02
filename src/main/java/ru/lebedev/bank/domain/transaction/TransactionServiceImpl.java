package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionDTO create(TransactionDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);

        transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    public TransactionDTO save(TransactionDTO transactionDTO){
        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> findAllByTargetAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findAllByTargetAccountId(id);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllBySourceAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findAllBySourceAccountId(id);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
