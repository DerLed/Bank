package ru.lebedev.bank.domain.statistics.dto;

import ru.lebedev.bank.domain.statistics.TypeOperation;


import java.time.LocalDateTime;

public class TransactionStatisticsDTO {

        private Long id;

        private LocalDateTime date;

        private TypeOperation type;

}
