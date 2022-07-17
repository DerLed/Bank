package ru.lebedev.bank.domain.checkingAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.AccountCardTransferFormDTO;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.card.CardService;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.checkingAccount.dto.TransactionFormDTO;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.exception.CloseDefaultAccountException;
import ru.lebedev.bank.validator.AccountCardTransferFormDTOValidator;
import ru.lebedev.bank.validator.TransactionFormDTOValidator;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/checking")
@RequiredArgsConstructor
public class CheckingAccountMvcController {
    private final CheckingAccountService checkingAccountService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionFormDTOValidator transactionFormDTOValidator;
    private final AccountCardTransferFormDTOValidator accountCardTransferFormDTOValidator;

    @InitBinder("phoneTransactionForm")
    public void initPhoneBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(transactionFormDTOValidator);
    }

    @InitBinder("cardTransactionForm")
    public void iniCardBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(accountCardTransferFormDTOValidator);
    }


    @GetMapping
    public String showAll(Model model, Principal principal) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
        model.addAttribute("accounts", accounts);
        return "accounts/account-list";
    }

    @GetMapping("/transfer-phone")
    public String showAccountsTransfer(Model model, Principal principal) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
        model.addAttribute("phoneTransactionForm", new TransactionFormDTO());
        model.addAttribute("accounts", accounts);
        return "accounts/account-transfer";
    }

    @PostMapping("/transfer-phone")
    public String accountsTransfer(@ModelAttribute("phoneTransactionForm") @Valid TransactionFormDTO transactionFormDTO,
                                   BindingResult bindingResult,
                                   Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
            model.addAttribute("accounts", accounts);
            return "accounts/account-transfer";
        }

        accountService.transferMoneyByUserPhoneNumber(transactionFormDTO.getAccount().getId(),
                transactionFormDTO.getPhoneNumber(), transactionFormDTO.getAmount());

        return "redirect:/client";
    }


    @GetMapping("/transfer-card")
    public String showCardTransfer(Model model, Principal principal) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
        List<CardDTO> cards = cardService.findByClientLogin(principal.getName());
        model.addAttribute("cardTransactionForm", new AccountCardTransferFormDTO());
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cards);
        return "accounts/account-transfer-card";
    }

    @PostMapping("/transfer-card")
    public String cardTransfer(@ModelAttribute("cardTransactionForm") @Valid AccountCardTransferFormDTO accountCardTransferFormDTO,
                               BindingResult bindingResult,
                               Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
            List<CardDTO> cards = cardService.findByClientLogin(principal.getName());
            model.addAttribute("accounts", accounts);
            model.addAttribute("cards", cards);
            return "accounts/account-transfer-card";
        }

        accountService.transferMoneyByCardNumber(accountCardTransferFormDTO.getAccountDTO().getId(),
                accountCardTransferFormDTO.getCardDTO().getCardNumber(), accountCardTransferFormDTO.getAmount());

        return "redirect:/client";
    }


    @GetMapping("/add-money")
    public String showAddMoney(Model model, Principal principal) {
        List<CheckingAccountDTO> accounts = checkingAccountService.findByClientLogin(principal.getName());
        model.addAttribute("accounts", accounts);
        return "accounts/account-add-money";
    }

    @PostMapping("/add-money")
    public String accountsAddMoney(@RequestParam String amount,
                                   @RequestParam CheckingAccountDTO account,
                                   Principal principal) {

        checkingAccountService.addMoney(account.getId(), new BigDecimal(amount));
        return "redirect:/client";
    }

    @GetMapping("/new")
    public String newCheckingAccount(Model model) {
        model.addAttribute("accountCreate", new CheckingAccountDTO());
        return "accounts/checking/new";
    }

    @PostMapping("/new")
    public String newCheckingAccountCreate(@ModelAttribute("accountCreate") @Valid CheckingAccountDTO checkingAccountDTO,
                                           Principal principal) {
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        checkingAccountDTO.setClientDTO(clientDTO);
        checkingAccountService.save(checkingAccountDTO);
        return "redirect:/client";
    }


    @GetMapping("/close/{accountId}")
    public String viewClose(@PathVariable Long accountId, Principal principal, Model model) {
        CheckingAccountDTO closeAccount = checkingAccountService.findById(accountId).orElseThrow();
        List<CheckingAccountDTO> allAccounts = checkingAccountService.findByClientLogin(principal.getName());
        boolean isOwner = allAccounts.stream().anyMatch(a -> a.getId().equals(closeAccount.getId()));
        if (isOwner) {
            model.addAttribute("closeAccount", closeAccount);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        return "accounts/account-close";
    }

    @PostMapping("/close/{accountId}")
    public String close(@PathVariable Long accountId, Model model, Principal principal) {

        CheckingAccountDTO closeAccount = checkingAccountService.findById(accountId).orElseThrow();
        if (closeAccount.getIsDefault()) {
            model.addAttribute("error",
                    "Данный аккаунт нельзя закрыть, это аккаунт по умолчанию.");
            return "accounts/account-close";
        }
        List<CheckingAccountDTO> allAccounts = checkingAccountService.findByClientLogin(principal.getName());
        boolean isOwner = allAccounts.stream().anyMatch(a -> a.getId().equals(closeAccount.getId()));
        if (isOwner) {
            checkingAccountService.deleteById(accountId);
        }

        return "redirect:/client";
    }


}
