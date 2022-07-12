package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.account.mapper.AccountMapper;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;
import ru.lebedev.bank.domain.transaction.mapper.TransactionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionDTO> findById(Long id) {
        return transactionRepository.findById(id).map(transactionMapper::toDTO);
    }

    @Override
    @Transactional
    public TransactionDTO save(TransactionDTO dto) {
        Transaction transaction = transactionMapper.toEntity(dto);
        transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    @Transactional
    public TransactionDTO updateById(Long id, TransactionDTO dto) {
        return transactionRepository.findById(id).map(transaction -> {
                    transaction.setDate(dto.getDate());
                    transaction.setAmount(dto.getAmount());
                    transaction.setSourceAccount(accountMapper.toEntity(dto.getSourceAccount()));
                    transaction.setTargetAccount(accountMapper.toEntity(dto.getTargetAccount()));
                    transaction.setStatus(dto.getStatus());
                    return transactionMapper.toDTO(transactionRepository.save(transaction));
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    return transactionMapper.toDTO(transactionRepository.save(transactionMapper.toEntity(dto)));
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
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
