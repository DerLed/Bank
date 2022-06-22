package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.CardPlan;

public interface AccountPlanRepository extends JpaRepository<CardPlan, Long> {

}