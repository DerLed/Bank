package ru.lebedev.bank.domain.cardPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.lebedev.bank.domain.card.Card;
import ru.lebedev.bank.domain.cardPlan.CardPlan;

public interface CardPlanRepository extends JpaRepository<CardPlan, Long> {

}
