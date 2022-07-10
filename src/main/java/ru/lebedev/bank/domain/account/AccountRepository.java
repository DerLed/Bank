package ru.lebedev.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndIsClosedFalse(Long id);

    List<Account> findByClientUserLoginAndIsClosedFalse(String login);

    List<Account> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);

    @Query("select a from Card c join c.account a on c.cardNumber = :cardNumber " +
            "and a.isClosed = false and c.isClosed = false")
    Optional<Account> findByCardNumber(String cardNumber);

    @Modifying(clearAutomatically = true)
    @Query("update Account set isClosed = true where id = :id")
    void closeById(Long id);

}
