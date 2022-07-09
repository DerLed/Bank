package ru.lebedev.bank.domain.account.checking;

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
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
//
    Optional<CheckingAccount> findByIdAndIsClosedFalse(Long id);
////
////    List<Account> findByClientId(Long ClientId);
    List<CheckingAccount> findByClientUserLoginAndIsClosedFalse(String login);
////
////
////    List<Account> findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(String login, TypeAccount type);
    List<CheckingAccount> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);
////
////    @Query("select a from Card c join c.account a where c.cardNumber = :cardNumber " +
////            "and a.isClosed = false and c.isClosed = false and a.client.user.status = ru.lebedev.bank.domain.Status.ACTIVE ")
////    Optional<Account> findByCardNumber(String cardNumber);
////
////
////    @Query("select a from Account a where a.client = (select a1.client from Account a1 WHERE id = :id) and a.isDefault = true ")
////    Optional<Account> findByAccountIdDefaultAccount(Long id);
////
////
////
    @Modifying(clearAutomatically = true)
//    @Query("update Account set isClosed = true where id = :id")
    @Query("update CheckingAccount set isClosed = true where id = :id")
    void closeById(Long id);
////
////    List<Account> findByClientIdAndIsDefaultTrueAndIsClosedFalse(Long clientId);
}
