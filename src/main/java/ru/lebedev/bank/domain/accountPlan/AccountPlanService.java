package ru.lebedev.bank.domain.accountPlan;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface AccountPlanService {

    List<AccountPlanDTO> findAll();
    List<AccountPlanDTO> findByType(TypeAccount typeAccount);

    AccountPlanDTO save(@NotNull AccountPlanDTO accountPlanDTO);

    Optional<AccountPlanDTO> findById(@NotNull Long id);

    void delete(@NotNull AccountPlanDTO accountPlanDTO);

    void deleteById(@NotNull Long id);
}
