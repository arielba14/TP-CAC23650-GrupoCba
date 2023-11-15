package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.entities.Transfer;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AccountMapper {
    public Account dtoToAccountMap(AccountDto dto){
        Account account = new Account();
        account.setId(dto.getId());
        account.setType(dto.getType());
        account.setAlias(dto.getAlias());
        account.setNumber(dto.getNumber());
        account.setActive(dto.getActive());
        account.setAmount(dto.getAmount());
        if (dto.getUser() != null){
            account.setUser(UserMapper.dtoToUserMap(dto.getUser()));
        }
        return account;
    }

    public AccountDto accountToDtoMap(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setNumber(account.getNumber());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setActive(account.getActive());
        if (account.getUser() != null){
            dto.setUser(UserMapper.userToDtoMap(account.getUser()));
        }
        return dto;
    }
}
