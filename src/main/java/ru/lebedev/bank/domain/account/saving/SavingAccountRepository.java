package ru.lebedev.bank.domain.account.saving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

    Optional<SavingAccount> findByIdAndIsClosedFalse(Long id);

    List<SavingAccount> findByClientId(Long ClientId);

    List<SavingAccount> findByClientUserLoginAndIsClosedFalse(String login);

    List<SavingAccount> findByClientPhoneNumberAndIsClosedFalse(String phoneNumber);

    List<SavingAccount> findAllByIsClosedFalse();

    @Modifying(clearAutomatically = true)
    @Query("update SavingAccount set isClosed = true where id = :id")
    void closeById(Long id);

}
