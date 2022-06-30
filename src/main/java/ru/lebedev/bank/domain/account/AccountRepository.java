package ru.lebedev.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lebedev.bank.domain.account.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //void closeById(Long id);
    List<Account> findByClientId(Long ClientId);
    List<Account> findByClientUserLogin(String login);

    //@Query("select a from Account a where a.client.phoneNumber = :phoneNumber")
    List<Account> findByClientPhoneNumber(String phoneNumber);
}
