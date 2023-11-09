package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.dtos.TransactionDto;
import com.cac.tpcacfinal.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private final TransactionService service;

    public TransactionController(TransactionService transactionService){
        this.service = transactionService;
    }

    @GetMapping(value="/transactions")
    public List<TransactionDto> getTransactions(){
        return service.getTransactions();
    }

    @GetMapping(value = "/transaction/{id}")
    public TransactionDto getTransactionById(@PathVariable Long id){
        return service.getTransactionById(id);
    }

    @PostMapping(value = "/insertTransaction")
    public void saveTransaction(@RequestBody TransactionDto transaction){
        service.saveTransaction(transaction);
    }

    @PutMapping (value = "/updateTransaction")
    public void updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transaction){
        TransactionDto transactionId = service.getTransactionById(id);
        transactionId.setAccount(transaction.getAccount());
        transactionId.setType(transaction.getType());
        transactionId.setDate(transaction.getDate());
        transactionId.setAmount(transaction.getAmount());
        transactionId.setDescription(transaction.getDescription());
        service.saveTransaction(transactionId);
    }

    @DeleteMapping (value = "/deleteTransaction/{id}")
    public void deleteTransaction(@PathVariable Long id){
        service.deleteTransaction(id);
    }
}
