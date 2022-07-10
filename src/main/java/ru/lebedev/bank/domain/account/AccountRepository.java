package ru.lebedev.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import ru.lebedev.bank.domain.account.Account;
//import ru.lebedev.bank.domain.accountPlan.TypeAccount;
//
//import java.util.List;
//import java.util.Optional;
//
public interface AccountRepository extends JpaRepository<Account, Long> {
//
    Optional<Account> findByIdAndIsClosedFalse(Long id);
////
////    List<Account> findByClientId(Long ClientId);
    List<Account> findByClientUserLoginAndIsClosedFalse(String login);

    List<Account> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);
////
    @Query("select a from Card c join c.account a on c.cardNumber = :cardNumber " +
            "and a.isClosed = false and c.isClosed = false")
    Optional<Account> findByCardNumber(String cardNumber);
////
////
////    @Query("select a from Account a where a.client = (select a1.client from Account a1 WHERE id = :id) and a.isDefault = true ")
////    Optional<Account> findByAccountIdDefaultAccount(Long id);
////
////
////
    @Modifying(clearAutomatically = true)
    @Query("update Account set isClosed = true where id = :id")
    void closeById(Long id);
////
////    List<Account> findByClientIdAndIsDefaultTrueAndIsClosedFalse(Long clientId);
}
