package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.Dto.AccountDto;
import com.cac.tpcacfinal.entities.Dto.UserDto;
import com.cac.tpcacfinal.entities.Transaction;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AccountMapper {
    public Account dtoToAccountMap(AccountDto dto){
        Account account = new Account();
        account.setTipo(dto.getTipo());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        return account;
    }

    public AccountDto accountToDtoMap(Account account){
        AccountDto dto = new AccountDto();
        List<Long> transactions = new ArrayList<>();
        dto.setId(account.getId());
        dto.setTipo(account.getTipo());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        if (account.getUser() != null){
            dto.setUser(UserMapper.userToDtoMap(account.getUser()));
        }
        if (account.getTransactions() != null){
            for(Transaction t: account.getTransactions()){
                transactions.add(t.getId());
            }
            dto.setTransactions(transactions);
        }
        return dto;
    }
}
