package ru.lebedev.bank.utills;


import ru.lebedev.bank.domain.savingAccount.SavingAccount;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class DepositCalc {
    public static BigDecimal depositCalc(SavingAccount account){

        BigDecimal startAmount = account.getAmount();
        BigDecimal percent = account.getAccountPlan().getPercent();
        long numDay = Duration.between(account.getDateOpened(), LocalDateTime.now()).toDays();
        int dayOfYear = LocalDateTime.now().getDayOfYear();

        BigDecimal saving = ((startAmount
                              .multiply(percent)
                              .multiply(BigDecimal.valueOf(numDay)))

                                      .divide(BigDecimal.valueOf(dayOfYear), 2, RoundingMode.HALF_UP))

                                            .divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP);

        return saving;


    }
}
