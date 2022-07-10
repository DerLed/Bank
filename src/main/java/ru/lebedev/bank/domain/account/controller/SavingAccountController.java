package ru.lebedev.bank.domain.account.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.account.saving.SavingAccountService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/saving")
@Tag(name = "Saving account controller")
@RequiredArgsConstructor
public class SavingAccountController {

    private final SavingAccountService savingAccountService;

    @GetMapping
    public ResponseEntity<List<SavingAccountDTO>> findAll () {
        List<SavingAccountDTO> accounts = savingAccountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SavingAccountDTO> save (@RequestBody @Valid SavingAccountDTO account) {
        SavingAccountDTO savedAccount = savingAccountService.save(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/close/{accountId}")
    public ResponseEntity<Void> close (@PathVariable Long accountId) {
        savingAccountService.deleteById(accountId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<SavingAccountDTO>> findByClientId (@PathVariable Long clientId) {
        List<SavingAccountDTO> accounts = savingAccountService.findByClientId(clientId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<List<SavingAccountDTO>> findByPhoneNumber (@PathVariable String phoneNumber) {
        List<SavingAccountDTO> accounts = savingAccountService.findByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
