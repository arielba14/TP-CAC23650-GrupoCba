package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.entities.Transfer;
import com.cac.tpcacfinal.services.UserService;
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
        account.setUpdate_at(dto.getUpdate_at());
        account.setCreated_at(dto.getCreated_at());
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
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdate_at(account.getUpdate_at());
        if (account.getUser() != null){
            dto.setUser(UserMapper.userToDtoMap(account.getUser()));
        }

        return dto;
    }
}
