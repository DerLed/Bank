package ru.lebedev.bank.domain.statistics;

import ru.lebedev.bank.domain.statistics.dto.TransactionStatisticsDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionStatisticService{

    List<TransactionStatisticsDTO> findAll();

    Optional<TransactionStatisticsDTO> findById(Long id);

    void deleteById(Long id);
}
