package ru.lebedev.bank.domain.statistics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatisticsRepository extends JpaRepository<TransactionStatistics, Long> {
}
