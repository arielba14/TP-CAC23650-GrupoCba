package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.mappers.AccountMapper;
import com.cac.tpcacfinal.mappers.TransactionMapper;
import com.cac.tpcacfinal.mappers.UserMapper;
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
    @Autowired
    private final UserService userService;

    public AccountController(AccountService service, UserService user){

        this.accountService = service;
        this.userService = user;
    }

    @GetMapping(value="/accounts")
    public List<AccountDto> getAccounts(){

        return accountService.getAccounts();
    }

    @GetMapping(value="/account/{id}")
    public AccountDto getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping(value = "/insertAccount")
    public void saveAccount(@RequestBody Account account){
        accountService.saveAccount(account);
    }

    @PutMapping(value = "/updateAccount/{id}")
    public void updateAccount(@PathVariable Long id, @RequestBody Account account){
        Account accountId = AccountMapper.dtoToAccountMap(accountService.getAccountById(id));
        accountId.setAlias(account.getAlias());
        accountId.setAmount(account.getAmount());
        accountId.setUser(account.getUser());
        accountId.setNumber(account.getNumber());
        accountId.setType(account.getType());
        accountId.setTransactions(account.getTransactions());
        accountService.saveAccount(accountId);
    }

    @DeleteMapping(value = "/deleteAccount/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }





}
