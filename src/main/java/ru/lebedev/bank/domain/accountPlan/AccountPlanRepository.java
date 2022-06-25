package ru.lebedev.bank.domain.accountPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.cardPlan.CardPlan;

public interface AccountPlanRepository extends JpaRepository<AccountPlan, Long> {

}
