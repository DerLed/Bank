package ru.lebedev.bank.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
