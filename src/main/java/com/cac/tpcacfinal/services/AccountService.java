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

    private AccountRepository accountRepository;

    private AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAccounts(){
        return accountRepository.findAll().stream().map(account -> AccountMapper.accountToDtoMap(account)).collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id){
        return AccountMapper.accountToDtoMap(accountRepository.findById(id).get());
    }

    public AccountDto createAccount(AccountDto dto){
        Account account = AccountMapper.dtoToAccountMap(dto);
        account.setActive(true);
        Account nueva = accountRepository.save(account);
        dto = AccountMapper.accountToDtoMap(nueva);
        return dto;
    }

    public AccountDto updateAccount(AccountDto dto){
        //hacer
        return dto;
    }

    public String deleteAccount(Long id){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (account.getActive()) {
                account.setActive(false);
                accountRepository.save(account);
                return "La cuenta con id " + id + " está inactiva";
            }else{
                return "La cuenta con id " + id + " ya se encuentra inactiva";
            }
        }else{
            return "La cuenta con id " + id + " no existe";
        }
    }
}
