package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account-plans")
@RequiredArgsConstructor
public class AccountPlanController {
    private final AccountPlanService accountPlanService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountPlanDTO> findById(@PathVariable Long id) {
        Optional<AccountPlanDTO> accountPlan = accountPlanService.findById(id);
        return accountPlan.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AccountPlanDTO>> findAll () {
        List<AccountPlanDTO> accountPlans = accountPlanService.findAll();
        return new ResponseEntity<>(accountPlans, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountPlanDTO> save (@RequestBody @Valid AccountPlanDTO accountPlanDTO) {
        AccountPlanDTO savedAccount = accountPlanService.save(accountPlanDTO);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody @Valid AccountPlanDTO accountPlanDTO) {
        accountPlanService.deleteById(accountPlanDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
