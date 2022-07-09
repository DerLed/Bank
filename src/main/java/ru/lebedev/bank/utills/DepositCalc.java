package ru.lebedev.bank.utills;

import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class DepositCalc {
    public static BigDecimal depositCalc(Account account){

        BigDecimal startAmount = account.getAmount();
//        BigDecimal percent = account.getAccountPlan().getPercent();
        long numDay = Duration.between(account.getDateOpened(), LocalDateTime.now()).toDays();
        int dayOfYear = LocalDateTime.now().getDayOfYear();

//        BigDecimal saving = ((startAmount
//                              .multiply(percent)
//                              .multiply(BigDecimal.valueOf(numDay)))
//
//                                      .divide(BigDecimal.valueOf(dayOfYear), 2, RoundingMode.HALF_UP))
//
//                                            .divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP);

//        return saving;
        return BigDecimal.ZERO;

    }
}
