package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;
import ru.lebedev.bank.domain.transaction.mapper.TransactionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionDTO> findAll() {
        return null;
    }

    @Override
    public Optional<TransactionDTO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public TransactionDTO save(TransactionDTO dto) {
        Transaction transaction = transactionMapper.toEntity(dto);
        transactionRepository.save(transaction);
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public TransactionDTO updateById(Long aLong, TransactionDTO dto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<TransactionDTO> findAllByTargetAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findAllByTargetAccountId(id);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
//
    @Override
    public List<TransactionDTO> findAllBySourceAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findAllBySourceAccountId(id);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transactionRepository.delete(transaction);
    }
}
