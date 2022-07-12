package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.exception.AccountPlanNotFoundException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/account-plans")
@RequiredArgsConstructor
public class AccountPlanController {
    private final AccountPlanService accountPlanService;

    @GetMapping(value = "/{id}")
    public AccountPlanDTO findById(@PathVariable Long id) {
        return accountPlanService.findById(id).orElseThrow(() -> new AccountPlanNotFoundException(id));
    }

    @GetMapping
    public List<AccountPlanDTO> findAll () {
        return accountPlanService.findAll();
    }

    @PostMapping
    public AccountPlanDTO save (@RequestBody @Valid AccountPlanDTO accountPlanDTO) {
        return accountPlanService.save(accountPlanDTO);
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid AccountPlanDTO accountPlanDTO) {
        accountPlanService.deleteById(accountPlanDTO.getId());
    }

}
