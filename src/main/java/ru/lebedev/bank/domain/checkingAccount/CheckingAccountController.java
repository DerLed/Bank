package ru.lebedev.bank.domain.checkingAccount;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.helper.validGroup.Create;


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
    public List<CheckingAccountDTO> findAll() {
        return checkingAccountService.findAll();
    }

    @PostMapping
    public CheckingAccountDTO save(@RequestBody @Validated({Create.class}) CheckingAccountDTO account) {
        return checkingAccountService.save(account);
    }

    @DeleteMapping("/close/{accountId}")
    public void close(@PathVariable Long accountId) {
        checkingAccountService.deleteById(accountId);
    }

    @GetMapping("/client/{clientId}")
    public List<CheckingAccountDTO> findByClientId(@PathVariable Long clientId) {
        return checkingAccountService.findByClientId(clientId);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public List<CheckingAccountDTO> findByPhoneNumber(@PathVariable String phoneNumber) {
        return checkingAccountService.findByPhoneNumber(phoneNumber);
    }

    @PutMapping("/phoneNumber/{accountId}")
    public void transferMoneyByUserPhoneNumber(@PathVariable @Parameter(description = "Id аккаунта списания")
                                                                       Long accountId,
                                                               @RequestBody @Parameter(description = "Номер телефона на который осуществляется перевод")
                                                                       String phoneNumber,
                                                               @RequestParam @Parameter(description = "Сумма перевода")
                                                                       BigDecimal amount) {
        accountService.transferMoneyByUserPhoneNumber(accountId, phoneNumber, amount);
    }

    @PutMapping("/cardNumber/{accountId}")
    @Operation(summary = "Transfer money by card number")
    public void transferMoneyByCardNumber(@PathVariable @Parameter(description = "Id аккаунта списания")
                                                                  Long accountId,
                                                          @RequestBody @Parameter(description = "Номер телефона на который осуществляется перевод")
                                                                  String cardNumber,
                                                          @RequestParam @Parameter(description = "Сумма перевода")
                                                                  BigDecimal amount) {
        accountService.transferMoneyByCardNumber(accountId, cardNumber, amount);
    }
}
