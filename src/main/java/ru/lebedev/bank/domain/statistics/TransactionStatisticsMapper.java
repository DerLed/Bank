package ru.lebedev.bank.domain.statistics;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.statistics.dto.TransactionStatisticsDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TransactionStatisticsMapper extends EntityMapper<TransactionStatisticsDTO, TransactionStatistics> {
}
