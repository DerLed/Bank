package ru.lebedev.bank.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.card.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
