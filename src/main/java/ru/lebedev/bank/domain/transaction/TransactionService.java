package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findAll();

    TransactionDTO save(TransactionDTO transactionRequestDTO);

    List<TransactionDTO> findAllByTargetAccountId(Long id);

    List<TransactionDTO> findAllBySourceAccountId(Long id);

    void delete(TransactionDTO transactionDTO);
}
