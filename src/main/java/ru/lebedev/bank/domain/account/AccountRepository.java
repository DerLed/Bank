package ru.lebedev.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.account.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //void closeById(Long id);
    List<Account> findByClientId(Long ClientId);
    List<Account> findByClientPhoneNumber(String phoneNumber);
}
