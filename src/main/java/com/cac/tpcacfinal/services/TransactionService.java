package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Transaction;
import com.cac.tpcacfinal.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElse(null);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }
}
