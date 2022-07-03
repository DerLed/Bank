package ru.lebedev.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndIsClosedFalse(Long id);

    List<Account> findByClientId(Long ClientId);
    List<Account> findByClientUserLogin(String login);

    //@Query("select a from Account a where a.client.user.login = :login and a.accountPlan.type = "LOAN" ")
    List<Account> findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(String login, TypeAccount type);
    //@Query("select a from Account a where a.client.phoneNumber = :phoneNumber")
    List<Account> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);

    @Query("select a from Card c join c.account a where c.cardNumber = :cardNumber " +
            "and a.isClosed = false and c.isClosed = false and a.client.user.status = ru.lebedev.bank.domain.Status.ACTIVE ")
    Optional<Account> findByCardNumber(String cardNumber);

    @Modifying(clearAutomatically = true)
    @Query("update Account set isClosed = true where id = :id")
    void closeById(Long id);

    List<Account> findByClientIdAndIsDefaultTrueAndIsClosedFalse(Long clientId);
}
