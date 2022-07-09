package ru.lebedev.bank.domain.account.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.checking.CheckingAccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/checking")
@Tag(name = "Checking account controller")
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<CheckingAccountDTO>> findAll () {
        List<CheckingAccountDTO> accounts = checkingAccountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CheckingAccountDTO> save (@RequestBody @Valid CheckingAccountDTO account) {
        CheckingAccountDTO savedAccount = checkingAccountService.save(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/close/{accountId}")
    public ResponseEntity<Void> close (@PathVariable Long accountId) {
        checkingAccountService.deleteById(accountId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CheckingAccountDTO>> findByClientId (@PathVariable Long clientId) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByClientId(clientId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<List<CheckingAccountDTO>> findByPhoneNumber (@PathVariable String phoneNumber) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

        @PutMapping("/phoneNumber/{accountId}")
    public ResponseEntity<Void> transferMoneyByUserPhoneNumber (@PathVariable @Parameter(description = "Id аккаунта списания")
                                                                Long accountId,
                                                                @RequestBody @Parameter(description = "Номер телефона на который осуществляется перевод")
                                                                String phoneNumber,
                                                                @RequestParam @Parameter(description = "Сумма перевода")
                                                                        BigDecimal amount) {
            accountService.transferMoneyByUserPhoneNumber(accountId, phoneNumber, amount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
