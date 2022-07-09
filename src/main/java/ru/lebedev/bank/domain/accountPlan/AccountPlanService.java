package ru.lebedev.bank.domain.accountPlan;

import org.springframework.validation.annotation.Validated;
import ru.lebedev.bank.domain.BaseRepository;
import ru.lebedev.bank.domain.BaseService;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


public interface AccountPlanService extends BaseService<AccountPlanDTO, Long> {

}
