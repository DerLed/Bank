package ru.lebedev.bank.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AccountDTO>> findByClientId (@PathVariable Long clientId) {
        List<AccountDTO> accounts = accountService.findByClientId(clientId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<List<AccountDTO>> findByPhoneNumber (@PathVariable String phoneNumber) {
        List<AccountDTO> accounts = accountService.findByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/cardNumber/{cardNumber}")
    public ResponseEntity<Optional<AccountDTO>> findByCardNumber (@PathVariable String cardNumber) {
        Optional<AccountDTO> account = accountService.findByCardNumber(cardNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getHistory (@PathVariable Long accountId) {
        List<TransactionDTO> transactions = accountService.getHistory(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll () {
        List<AccountDTO> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> save (@RequestBody @Valid AccountDTO account) {
        AccountDTO savedAccount = accountService.save(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/close/{accountId}")
    public ResponseEntity<Void> close (@PathVariable Long accountId) {
        accountService.close(accountId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     *
     * @param accountId id аккаунта списания денег
     * @param cardNumber номер карты для получения денег
     * @param amount сумма
     * @return 200
     */
    @PutMapping("/cardNumber/{accountId}")
    public ResponseEntity<Void> transferMoneyByCardNumber (@PathVariable Long accountId,
                                                           @RequestBody String cardNumber,
                                                           @RequestParam BigDecimal amount) {
        accountService.transferMoneyByCardNumber(accountId, cardNumber, amount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/phoneNumber/{accountId}")
    public ResponseEntity<Void> transferMoneyByUserPhoneNumber (@PathVariable Long accountId,
                                                                @RequestBody String phoneNumber,
                                                                @RequestParam BigDecimal amount) {
        accountService.transferMoneyByUserPhoneNumber(accountId, phoneNumber, amount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
