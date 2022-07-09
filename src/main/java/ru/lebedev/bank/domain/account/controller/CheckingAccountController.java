package ru.lebedev.bank.domain.account.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.checking.CheckingAccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/checking")
@Tag(name = "Account controller")
@RequiredArgsConstructor
public class CheckingAccountController {
//
    private final CheckingAccountService checkingAccountService;

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
//
//    @GetMapping("/client/{clientId}")
//    public ResponseEntity<List<AccountDTO>> findByClientId (@PathVariable Long clientId) {
////        List<AccountDTO> accounts = accountService.findByClientId(clientId);
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }
//
//    @GetMapping("/phoneNumber/{phoneNumber}")
//    public ResponseEntity<List<AccountDTO>> findByPhoneNumber (@PathVariable String phoneNumber) {
////        List<AccountDTO> accounts = accountService.findByPhoneNumber(phoneNumber);
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }
//
//    @GetMapping("/cardNumber/{cardNumber}")
//    public ResponseEntity<Optional<AccountDTO>> findByCardNumber (@PathVariable String cardNumber) {
//        Optional<AccountDTO> account = accountService.findByCardNumber(cardNumber);
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }
//
//    @GetMapping("/history/{accountId}")
//    public ResponseEntity<List<TransactionDTO>> getHistory (@PathVariable Long accountId) {
//        List<TransactionDTO> transactions = accountService.getHistory(accountId);
//        return new ResponseEntity<>(transactions, HttpStatus.OK);
//    }
//

//
//    /**
//     *
//     * @param accountId id аккаунта списания денег
//     * @param cardNumber номер карты для получения денег
//     * @param amount сумма
//     * @return 200
//     */
//    @PutMapping("/cardNumber/{accountId}")
//    @Operation(summary = "Transfer money by card number")
//    public ResponseEntity<Void> transferMoneyByCardNumber (@PathVariable Long accountId,
//                                                           @RequestBody String cardNumber,
//                                                           @RequestParam BigDecimal amount) {
//        accountService.transferMoneyByCardNumber(accountId, cardNumber, amount);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @PutMapping("/phoneNumber/{accountId}")
//    public ResponseEntity<Void> transferMoneyByUserPhoneNumber (@PathVariable @Parameter(description = "Id аккаунта списания")
//                                                                Long accountId,
//                                                                @RequestBody @Parameter(description = "Номер телефона на который осуществляется перевод")
//                                                                String phoneNumber,
//                                                                @RequestParam @Parameter(description = "Сумма перевода")
//                                                                BigDecimal amount) {
//        accountService.transferMoneyByUserPhoneNumber(accountId, phoneNumber, amount);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
