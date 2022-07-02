package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account_plans")
@RequiredArgsConstructor
public class AccountPlanController {
    private final AccountPlanService accountPlanService;

    @GetMapping
    public ResponseEntity<List<AccountPlanDTO>> findAll () {
        List<AccountPlanDTO> accountPlans = accountPlanService.findAll();
        return new ResponseEntity<>(accountPlans, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountPlanDTO> save (@RequestBody @Valid AccountPlanDTO account) {
        AccountPlanDTO savedAccount = accountPlanService.save(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

}
