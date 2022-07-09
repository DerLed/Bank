package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionMvcController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("/history/{accountId}")
    public String history(@PathVariable Long accountId, Model model){
        List<TransactionDTO> sourceTransactions = transactionService.findAllBySourceAccountId(accountId);
        List<TransactionDTO> targetTransactions = transactionService.findAllByTargetAccountId(accountId);
        List<TransactionDTO> allTransactions = Stream.concat(sourceTransactions.stream(),
                                                             targetTransactions.stream())
                                                .distinct().collect(Collectors.toList());

        model.addAttribute("transactions", allTransactions);
        model.addAttribute("accountId", accountId);
        return "transaction/transaction-list";
    }
}
