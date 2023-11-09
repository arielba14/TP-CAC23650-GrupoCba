package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.mappers.AccountMapper;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.repositories.AccountRepository;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public List<AccountDto> getAccounts(){
        return accountRepository.findAll().stream().map(account -> AccountMapper.accountToDtoMap(account)).collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id){
        return AccountMapper.accountToDtoMap(accountRepository.findById(id).orElse(null));
    }

    public void createAccount(AccountDto account){
        accountRepository.save(AccountMapper.dtoToAccountMap(account));
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
