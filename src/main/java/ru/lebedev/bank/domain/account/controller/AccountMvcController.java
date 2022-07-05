package ru.lebedev.bank.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;
import ru.lebedev.bank.domain.transaction.dto.TransactionFormDTO;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.exception.UserAlreadyExistException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountMvcController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountPlanService accountPlanService;

    @GetMapping("/{type}")
    public String accountsLoan(@PathVariable("type") TypeAccount typeAccount, Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), typeAccount);
        model.addAttribute("accounts", accounts);
        model.addAttribute("typeAccount", typeAccount.toString());
        return "accounts/account-list";
    }

    @GetMapping("/transfer")
    public String showAccountsTransfer(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
        model.addAttribute("form", new TransactionFormDTO());
        model.addAttribute("accounts", accounts);
        return "accounts/account-transfer";
    }

    @PostMapping("/transfer")
    public String accountsTransfer(@ModelAttribute("form") @Valid TransactionFormDTO transactionFormDTO,
                                   BindingResult bindingResult,
                                   Principal principal, Model model){
        List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", accounts);
            return "accounts/account-transfer";
        }

        try {
            accountService.transferMoneyByUserPhoneNumber(transactionFormDTO.getAccount().getId(),
                    transactionFormDTO.getPhoneNumber(), transactionFormDTO.getAmount());
        }catch (AccountTransferException e){
            bindingResult.rejectValue("phoneNumber", "phoneNumber","Данного номера нет в системе.");
            model.addAttribute("form", transactionFormDTO);
            model.addAttribute("accounts", accounts);
            return "accounts/account-transfer";
        }

        return "redirect:/client";
    }

    @GetMapping("/add-money")
    public String showAddMoney(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
        model.addAttribute("accounts", accounts);

        return "accounts/account-add-money";
    }

    @PostMapping("/add-money")
    public String accountsAddMoney(@RequestParam String amount,
                                   @RequestParam AccountDTO account,
                                   Principal principal){

        accountService.addMoney(account.getId(), new BigDecimal(amount));
        return "redirect:/client";
    }

    @GetMapping("/{type}/new")
    public String newCheckingAccount(@PathVariable("type") TypeAccount typeAccount, Principal principal, Model model){
        List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
        model.addAttribute("accountCreate", new AccountCreateDTO());
        model.addAttribute("checkingAccounts", accounts);
        model.addAttribute("typeAccount", typeAccount.toString());
        model.addAttribute("accountPlans", accountPlanService.findByType(typeAccount));
        return "accounts/account-new";
    }

    @PostMapping("/{type}/new")
    public String newCheckingAccountCreate(@PathVariable("type") TypeAccount typeAccount,
                                           @ModelAttribute("accountCreate") AccountCreateDTO accountCreateDTO,
                                           BindingResult bindingResult,
                                           Principal principal,
                                           Model model){

        if (bindingResult.hasErrors()) {
            List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
            model.addAttribute("checkingAccounts", accounts);
            model.addAttribute("accountPlans", accountPlanService.findByType(typeAccount));
            return "accounts/account-new";
        }
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();

        accountCreateDTO.setClient(clientDTO);
        accountService.create(accountCreateDTO);
        return "redirect:/client";
    }

    @GetMapping("/history/{accountId}")
    public String newCheckingAccount(@PathVariable Long accountId, Model model){
        List<TransactionDTO> transactions = accountService.getHistory(accountId);
        model.addAttribute("transactions", transactions);
        model.addAttribute("accountId", accountId);
        return "transaction/transaction-list";
    }

}
