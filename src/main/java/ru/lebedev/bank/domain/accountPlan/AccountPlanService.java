package ru.lebedev.bank.domain.accountPlan;

import java.util.List;
import java.util.Optional;

public interface AccountPlanService {

    List<AccountPlanDTO> findAll();
    AccountPlanDTO save(AccountPlanDTO accountPlanDTO);
    Optional<AccountPlanDTO> findById(Long id);
    void deleteById(Long id);
}
