package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Transaction;
import com.cac.tpcacfinal.entities.dtos.TransactionDto;
import com.cac.tpcacfinal.mappers.TransactionMapper;
import com.cac.tpcacfinal.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public List<TransactionDto> getTransactions(){

        return transactionRepository.findAll().stream().map(trans -> TransactionMapper.transactionToDtoMap(trans)).collect(Collectors.toList());
    }

    public TransactionDto getTransactionById(Long id){
        return TransactionMapper.transactionToDtoMap(transactionRepository.findById(id).orElse(null));
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }
}
