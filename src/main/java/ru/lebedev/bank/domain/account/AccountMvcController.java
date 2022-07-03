package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.transaction.TransactionDTO;
import ru.lebedev.bank.domain.transaction.TransactionFormDTO;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountMvcController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountPlanService accountPlanService;

    @GetMapping("/loan")
    public String accountsLoan(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginLoanAccounts(principal.getName());
        model.addAttribute("accounts", accounts);
        model.addAttribute("title", "Ваши кредиты");

        return "accounts/account-list";
    }

    @GetMapping("/saving")
    public String accountsSaving(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginSavingAccounts(principal.getName());
        model.addAttribute("accounts", accounts);
        model.addAttribute("title", "Ваши накопительные счета");

        return "accounts/account-list";
    }

    @GetMapping("/checking")
    public String accountsChecking(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginCheckingAccounts(principal.getName());
        model.addAttribute("accounts", accounts);
        model.addAttribute("title", "Ваши депозитные счета");

        return "accounts/account-list";
    }

    @GetMapping("/transfer")
    public String showAaccountsTransfer(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginCheckingAccounts(principal.getName());
        model.addAttribute("form", new TransactionFormDTO());
        model.addAttribute("accounts", accounts);
        model.addAttribute("title", "Ваши депозитные счета");

        return "accounts/account-transfer";
    }

    @PostMapping("/transfer")
    public String accountsTransfer(@ModelAttribute("form") @Valid TransactionFormDTO transactionFormDTO,
                                   BindingResult bindingResult,
                                   Principal principal){

        List<AccountDTO> accounts = accountService.findByClientLoginCheckingAccounts(principal.getName());
        if (bindingResult.hasErrors()) {
            return "accounts/account-new";
        }

        accountService.transferMoneyByUserPhoneNumber(transactionFormDTO.getAccount().getId(),
                transactionFormDTO.getPhoneNumber(), transactionFormDTO.getAmount());

        return "redirect:accounts";
    }


    @GetMapping("/new")
    public String newAccount(Model model){
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accountPlans", accountPlanService.findAll());
        return "accounts/account-new";
    }

    @PostMapping("/new")
    public String newAccountCreate(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                                   BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return "accounts/account-new";
        }
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        accountDTO.setClient(clientDTO);
        accountService.save(accountDTO);
        return "redirect:/client";
    }

    @GetMapping("/checking/new")
    public String newCheckingAccount(Model model){
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accountPlans", accountPlanService.findByType(TypeAccount.CHECKING));
        return "accounts/account-new";
    }

    @PostMapping("/checking/new")
    public String newCheckingAccountCreate(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                                   BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return "accounts/account-new";
        }
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        accountDTO.setClient(clientDTO);
        accountDTO.setIsDefault(false);
        accountDTO.setIsClosed(false);
        accountService.save(accountDTO);
        return "redirect:/client";
    }

    @GetMapping("/history/{accountId}")
    public String newCheckingAccount(@PathVariable Long accountId, Model model){
        List<TransactionDTO> transactions = accountService.getHistory(accountId);
        model.addAttribute("transactions", transactions);
        return "transaction/transaction-list";
    }

}
