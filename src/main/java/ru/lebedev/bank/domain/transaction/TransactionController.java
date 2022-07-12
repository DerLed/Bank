package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> findAll(){
        return transactionService.findAll();
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid TransactionDTO transactionDTO){
        transactionService.delete(transactionDTO);
    }

}
