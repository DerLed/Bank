//package ru.lebedev.bank.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.lebedev.bank.domain.transaction.Transaction;
//import ru.lebedev.bank.domain.transaction.TransactionRepository;
//
//import java.util.List;
//
//@Controller
//public class TransactionController {
//    @Autowired
//    private TransactionRepository repository;
//
//    @GetMapping("/transaction")
//    public String transaction(Model model){
//        List<Transaction> t = repository.findAll();
//        model.addAttribute("t", t);
//        return "transaction";
//    }
//}
