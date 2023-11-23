package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.mappers.AccountMapper;
import com.cac.tpcacfinal.repositories.AccountRepository;
import com.cac.tpcacfinal.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<AccountDto> getAccounts(){
        return accountRepository.findAll().stream().map(account -> AccountMapper.accountToDtoMap(account)).collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id){
        if (accountRepository.existsById(id)){
            return AccountMapper.accountToDtoMap(accountRepository.findById(id).get());
        }else{
            return null;
        }

    }


    public Boolean ingresarDinero(Long id, BigDecimal importe){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            account.setAmount(account.getAmount().add(importe));
            accountRepository.save(account);
            return true;
        }else{
            return false;
        }

    }

    public AccountDto createAccount(AccountDto dto){
        Account account = AccountMapper.dtoToAccountMap(dto);
        account.setActive(true);
        account.setAmount(BigDecimal.ZERO);
        account.setCreated_at(LocalDateTime.now());
        account.setUpdate_at(LocalDateTime.now());
        account.setUser(userRepository.findById(dto.getUser().getId()).get());
        Account nueva = accountRepository.save(account);
        dto = AccountMapper.accountToDtoMap(nueva);
        return dto;
    }

    public AccountDto updateAccount(AccountDto dto){
        //hacer
        return dto;
    }

    public Boolean deleteAccount(Long id){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (account.getActive()) {
                account.setActive(false);
                accountRepository.save(account);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
