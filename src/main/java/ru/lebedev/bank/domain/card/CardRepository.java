package ru.lebedev.bank.domain.card;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import ru.lebedev.bank.domain.card.Card;
//
import java.util.List;
import java.util.Optional;
//
public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Card set isClosed = true where id = :id")
    void softDeleteById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Card set isClosed = true where account.id = :accountId")
    void softDeleteAllByAccountId(Long accountId);

    @Modifying(clearAutomatically = true)
    @Query("update Card set isBlocked = true where id = :id")
    void blockById(Long id);

    List<Card> findByClientIdAndIsClosedFalse(Long clientId);

    List<Card> findByClientUserLoginAndIsClosedFalse(String login);

    Optional<Card> findByIdAndIsClosedFalse(Long id);

    Optional<Card> findByCardNumber(String cardNumber);
}
