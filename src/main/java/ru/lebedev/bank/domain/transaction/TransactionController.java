package ru.lebedev.bank.domain.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

//    private final TransactionService transactionService;
//
//    @GetMapping
//    public ResponseEntity<List<TransactionDTO>> findAll(){
//        List<TransactionDTO> transactionDTOs = transactionService.findAll();
//        return new ResponseEntity<>(transactionDTOs, HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<String> delete(@RequestBody @Valid TransactionDTO transactionDTO){
//        transactionService.delete(transactionDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
