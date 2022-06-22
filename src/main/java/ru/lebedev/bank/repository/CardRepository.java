package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
