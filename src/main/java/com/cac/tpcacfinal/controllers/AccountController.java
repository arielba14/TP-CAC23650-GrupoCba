package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService service){

        this.accountService = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(){

        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(id));
    }





}
