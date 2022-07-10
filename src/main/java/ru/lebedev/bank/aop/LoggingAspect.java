package ru.lebedev.bank.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import ru.lebedev.bank.domain.statistics.TransactionStatistics;
import ru.lebedev.bank.domain.statistics.TransactionStatisticsRepository;
import ru.lebedev.bank.domain.statistics.TypeOperation;

/**
 * Данный класс срабатывает когда пользователь переводит средства по номеру карты или по номеру телефона
 * и производит запись в базу данных статистики.
 */
@Component
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final TransactionStatisticsRepository transactionStatisticsRepository;

    @After("execution(* ru.lebedev.bank.domain.account.AccountServiceImpl.transferMoneyBy*(..))")
    public void logCountTransfer(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature()
                .getName();

        TransactionStatistics transactionStatistics = TransactionStatistics.builder().build();

        if (methodName.toLowerCase().contains(TypeOperation.PHONE.toString().toLowerCase())){
            transactionStatistics.setType(TypeOperation.PHONE);
        }
        else if(methodName.toLowerCase().contains(TypeOperation.CARD.toString().toLowerCase())){
            transactionStatistics.setType(TypeOperation.CARD);
        }

        transactionStatisticsRepository.save(transactionStatistics);

    }

}
