package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
