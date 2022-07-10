package ru.lebedev.bank.domain.account.checking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

    Optional<CheckingAccount> findByIdAndIsClosedFalse(Long id);

    List<CheckingAccount> findByClientId(Long ClientId);

    List<CheckingAccount> findByClientUserLoginAndIsClosedFalse(String login);

    List<CheckingAccount> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);

    @Query("select a from CheckingAccount a where a.client = (select a1.client from Account a1 " +
            "WHERE a1.id = :id  ) and a.isDefault = true and a.isClosed = false")
    Optional<CheckingAccount> findByAccountIdDefaultAccount(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update CheckingAccount set isClosed = true where id = :id")
    void closeById(Long id);

    List<CheckingAccount> findByClientIdAndIsDefaultTrueAndIsClosedFalse(Long clientId);

}
