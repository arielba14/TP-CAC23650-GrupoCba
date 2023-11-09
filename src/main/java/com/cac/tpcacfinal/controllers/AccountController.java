package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.services.AccountService;
import com.cac.tpcacfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService service, UserService user){

        this.accountService = service;
    }

    @GetMapping(value="/accounts")
    public List<AccountDto> getAccounts(){

        return accountService.getAccounts();
    }

    @GetMapping(value="/account/{id}")
    public AccountDto getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping(value = "/account")
    public void createAccount(@RequestBody AccountDto account){
        accountService.createAccount(account);
    }

    @PutMapping(value = "/account/{id}")
    public void updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        AccountDto accountId = accountService.getAccountById(id);
        accountId.setAlias(account.getAlias());
        accountId.setAmount(account.getAmount());
        accountId.setUser(account.getUser());
        accountId.setNumber(account.getNumber());
        accountId.setType(account.getType());
        accountId.setTransactions(account.getTransactions());
        accountService.createAccount(accountId);
    }

    @DeleteMapping(value = "/account/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }





}
