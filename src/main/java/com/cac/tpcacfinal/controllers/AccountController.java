package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.exceptions.BankingExceptions;
import com.cac.tpcacfinal.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    private AccountController(AccountService service){
        this.accountService = service;
    }

    @GetMapping(value="/active")
    public ResponseEntity<List<AccountDto>> getAccountsActive(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountsActive());
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
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
        }catch(RuntimeException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(id, account));
        }catch(RuntimeException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccount(id));
    }

    @PatchMapping(value = "/ingreso/{id}")
    public ResponseEntity<Boolean> enterCash(@PathVariable Long id, @RequestBody TransferDto amount){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(accountService.enterCash(id, amount));
        }catch(RuntimeException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @PatchMapping(value = "/extraer/{id}")
    public ResponseEntity<Boolean> extractCash(@PathVariable Long id, @RequestBody TransferDto amount){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.extractCash(id, amount));
        }catch(RuntimeException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }




}
