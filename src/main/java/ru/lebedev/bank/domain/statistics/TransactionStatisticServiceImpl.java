package ru.lebedev.bank.domain.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.statistics.dto.TransactionStatisticsDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionStatisticServiceImpl implements TransactionStatisticService{

    private final TransactionStatisticsRepository transactionStatisticsRepository;
    private final TransactionStatisticsMapper transactionStatisticsMapper;

    @Override
    public List<TransactionStatisticsDTO> findAll() {
        return transactionStatisticsRepository.findAll().stream()
                .map(transactionStatisticsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionStatisticsDTO> findById(Long id) {
        return transactionStatisticsRepository.findById(id).map(transactionStatisticsMapper::toDTO);
    }


    @Override
    public void deleteById(Long id) {
        transactionStatisticsRepository.deleteById(id);
    }
}
