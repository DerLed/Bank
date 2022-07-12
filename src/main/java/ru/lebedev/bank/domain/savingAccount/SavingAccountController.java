package ru.lebedev.bank.domain.savingAccount;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.savingAccount.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.savingAccount.SavingAccountService;
import ru.lebedev.bank.domain.helper.validGroup.Create;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saving")
@Tag(name = "Saving account controller")
@RequiredArgsConstructor
public class SavingAccountController {

    private final SavingAccountService savingAccountService;

    @GetMapping
    public List<SavingAccountDTO> findAll () {
        return savingAccountService.findAll();
    }

    @PostMapping
    public SavingAccountDTO save (@RequestBody @Validated({Create.class}) SavingAccountDTO account) {
        return savingAccountService.save(account);
    }

    @DeleteMapping("/close/{accountId}")
    public void close (@PathVariable Long accountId) {
        savingAccountService.deleteById(accountId);
    }

    @GetMapping("/client/{clientId}")
    public List<SavingAccountDTO> findByClientId (@PathVariable Long clientId) {
        return savingAccountService.findByClientId(clientId);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public List<SavingAccountDTO> findByPhoneNumber (@PathVariable String phoneNumber) {
        return savingAccountService.findByPhoneNumber(phoneNumber);
    }

}
