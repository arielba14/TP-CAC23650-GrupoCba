package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private final AccountService service;

    public AccountController(AccountService service){
        this.service = service;
    }

    @GetMapping(value="/accounts")
    public List<Account> getAccounts(){
        return service.getAccounts();
    }

    @GetMapping(value="/account/{id}")
    public Account getAccountById(@PathVariable Long id){
        return service.getAccountById(id);
    }

    @PostMapping(value = "/insertAccount")
    public void saveAccount(@RequestBody Account account){
        service.saveAccount(account);
    }

    @PutMapping(value = "/updateAccount/{id}")
    public void updateAccount(@PathVariable Long id, @RequestBody Account account){
        Account accountId = service.getAccountById(id);
        accountId.setAlias(account.getAlias());
        accountId.setAmount(account.getAmount());
        accountId.setUser(account.getUser());
        accountId.setType(account.getType());
        accountId.setTransactions(account.getTransactions());
        service.saveAccount(accountId);
    }

    @DeleteMapping(value = "/deleteAccount/{id}")
    public void deleteAccount(@PathVariable Long id){
        service.deleteAccount(id);
    }





}
