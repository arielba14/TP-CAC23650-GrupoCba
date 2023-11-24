package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.AccountDto;
import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.exceptions.BankingExceptions;
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


    public Boolean ingresarDinero(Long id, TransferDto dto){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            account.setAmount(account.getAmount().add(dto.getAmount()));
            accountRepository.save(account);
            return true;
        }else{
            return false;
        }

    }

    public AccountDto createAccount(AccountDto dto){
        if (existsAccountByCbu(dto.getCbu()) || exitsAccountByAlias(dto.getAlias())){
            throw new BankingExceptions("Ya existe una cuenta con el CBU: " + dto.getCbu() + " o el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
        }else{
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

    }

    public AccountDto updateAccount(Long id, AccountDto dto){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (dto.getAlias()!= ""){
                AccountDto alias = findByAlias(dto.getAlias());
                if (dto.getId()!=alias.getId()){
                    throw new BankingExceptions("Ya existe una cuenta con el el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
                }else{
                    account.setAlias(dto.getAlias());
                }
            }
            if (dto.getCbu()!= ""){
                AccountDto cbu = findByCbu(dto.getCbu());
                if (dto.getId()!= cbu.getId()) {
                    throw new BankingExceptions("Ya existe una cuenta con el CBU: " + dto.getCbu() + ", imposiblre actualizar la cuenta");
                }else{
                    account.setCbu(dto.getCbu());
                }
            }
            return AccountMapper.accountToDtoMap(account);
        }else {
            throw  new BankingExceptions("No existe una cuenta con el id + " + id + ", imposible actualizar la cuenta");
        }
    }
    public AccountDto updateAccountFull(Long id, AccountDto dto){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            AccountDto alias = findByAlias(dto.getAlias());
            if (dto.getId()!=alias.getId()){
                throw new BankingExceptions("Ya existe una cuenta con el el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
            }else{
                account.setAlias(dto.getAlias());
            }
            AccountDto cbu = findByCbu(dto.getCbu());
            if (dto.getId()!= cbu.getId()) {
                throw new BankingExceptions("Ya existe una cuenta con el CBU: " + dto.getCbu() + ", imposiblre actualizar la cuenta");
            }else{
                account.setCbu(dto.getCbu());
            }
            return AccountMapper.accountToDtoMap(account);
        }else {
            throw new BankingExceptions("No existe una cuenta con el id + " + id + ", imposible actualizar la cuenta");
        }
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

    public Boolean existsAccountByCbu(String cbu){
        Account account = accountRepository.findAccountByCbu(cbu);
        return account != null;
    }

    public Boolean exitsAccountByAlias(String alias){
        Account account = accountRepository.findAccountByAlias(alias);
        return account != null;
    }

    public AccountDto findByCbu(String cbu){
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account != null){
            return AccountMapper.accountToDtoMap(account);
        }else{
            return null;
        }
    }
    public AccountDto findByAlias(String alias){
        Account account = accountRepository.findAccountByAlias(alias);
        if (account != null){
            return AccountMapper.accountToDtoMap(account);
        }else{
            return null;
        }
    }
}
