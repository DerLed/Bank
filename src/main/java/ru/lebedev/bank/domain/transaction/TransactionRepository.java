package ru.lebedev.bank.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.transaction.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTargetAccountId(Long id);

    List<Transaction> findAllBySourceAccountId(Long id);
}
