package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.dtos.TransactionDto;
import com.cac.tpcacfinal.entities.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionMapper {
    public Transaction dtoToTransactionMap(TransactionDto dto){
        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setType(dto.getType());
        transaction.setDate(dto.getDate());
        return transaction;
    }

    public TransactionDto transactionToDtoMap(Transaction transaction){
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        dto.setDate(transaction.getDate());
        dto.setDescription(transaction.getDescription());
        if (transaction.getAccount() != null){
            dto.setAccount(AccountMapper.accountToDtoMap(transaction.getAccount()));
        }
        return dto;
    }
}
