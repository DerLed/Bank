package ru.lebedev.bank.domain.cardPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.cardPlan.CardPlan;

public interface CardPlanRepository extends JpaRepository<CardPlan, Long> {

}
