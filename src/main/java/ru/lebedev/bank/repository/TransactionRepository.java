package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
