package ru.lebedev.bank.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.savingAccount.SavingAccount;
import ru.lebedev.bank.domain.savingAccount.SavingAccountRepository;
import ru.lebedev.bank.domain.savingAccount.SavingAccountService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс производит автоматическое закрытие вкладов по истечении их срока.
 * Проверка производится каждый день в 02:00.
 * Начисление процентов по вкладу производится автоматически в service.
 * Количество закрытых вкладов отображается в логере в консоли.
 */
@Component
@RequiredArgsConstructor
public class CloseSavingAccountScheduled {

    private Logger logger = Logger.getLogger(CloseSavingAccountScheduled.class.getName());
    private final SavingAccountRepository savingAccountRepository;
    private final SavingAccountService savingAccountService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void closeSavingAccountScheduled(){
        List<SavingAccount> savingAccounts = savingAccountRepository.findAllByIsClosedFalse();
        Long countClosedAccount = savingAccounts.stream()
                .filter(hasEnded)
                .peek((a) -> savingAccountService.deleteById(a.getId()))
                .count();

        logger.log(Level.INFO, String.format("EGAR-BANK: Закрыто %d вкладов", countClosedAccount) );
    }

    Predicate<SavingAccount> hasEnded = acc -> {
        LocalDateTime dateOpened = acc.getDateOpened();
        LocalDateTime dateClosed = dateOpened.minusMonths(acc.getPeriod());
        return LocalDateTime.now().isAfter(dateClosed);
    };
}
