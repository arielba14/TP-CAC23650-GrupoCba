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

    public List<AccountDto> getAccountsActive(){
        List<AccountDto> lista =  accountRepository.findAll().stream().filter(Account::getActive).map(account -> AccountMapper.accountToDtoMap(account)).collect(Collectors.toList());
        lista.forEach(accountDto -> accountDto.getUser().setPassword("*********"));
        return lista;
    }

    public List<AccountDto> getAccounts(){
        List<AccountDto> lista = accountRepository.findAll().stream().map(account -> AccountMapper.accountToDtoMap(account)).collect(Collectors.toList());
        lista.forEach(accountDto -> accountDto.getUser().setPassword("*********"));
        return lista;
    }

    public AccountDto getAccountById(Long id){
        if (accountRepository.existsById(id)){
            AccountDto dto = AccountMapper.accountToDtoMap(accountRepository.findById(id).get());
            dto.getUser().setPassword("*********");
            return dto;
        }else{
            return null;
        }

    }


    public Boolean ingresarDinero(Long id, TransferDto dto){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (account.getActive()){
                account.setAmount(account.getAmount().add(dto.getAmount()));
                accountRepository.save(account);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }

    public AccountDto createAccount(AccountDto dto){
        if ((dto.getAlias()==null) || (dto.getType()==null)){
            throw new BankingExceptions("Los datos Alias y tipo de cuenta son obligatorios, imposible crear la cuenta");
        }else {
            if (exitsAccountByAlias(dto.getAlias())) {
                throw new BankingExceptions("Ya existe una cuenta con el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
            } else {
                Account account = AccountMapper.dtoToAccountMap(dto);
                account.setActive(true);
                account.setCbu(generarCBU());
                account.setAmount(BigDecimal.ZERO);
                account.setCreated_at(LocalDateTime.now());
                account.setUpdate_at(LocalDateTime.now());
                account.setUser(userRepository.findById(dto.getUser().getId()).get());
                Account nueva = accountRepository.save(account);
                dto = AccountMapper.accountToDtoMap(nueva);
                dto.getUser().setPassword("*********");
                return dto;
            }
        }

    }

    public AccountDto updateAccount(Long id, AccountDto dto){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (account.getActive()){
                if (dto.getAlias()!= null){
                    AccountDto alias = findByAlias(dto.getAlias());
                    if (alias != null){
                        if (dto.getId()!=alias.getId()){
                            throw new BankingExceptions("Ya existe una cuenta con el el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
                        }else{
                            account.setAlias(dto.getAlias());
                        }
                    }else{
                        account.setAlias(dto.getAlias());
                    }
                }
            }else{
                throw new BankingExceptions("La cuenta no está activa, imposible actualizar");
            }
            account.getUser().setPassword("*********");
            return AccountMapper.accountToDtoMap(account);
        }else {
            throw  new BankingExceptions("No existe una cuenta con el id + " + id + ", imposible actualizar la cuenta");
        }
    }
    public AccountDto updateAccountFull(Long id, AccountDto dto){
        if (accountRepository.existsById(id)){
            if ((dto.getCbu()== null) || (dto.getAlias()==null) || (dto.getType()==null)){
                throw new BankingExceptions("Los datos CBU, Alias y tipo de cuenta son obligatorios, imposible actualizar la cuenta");
            }else {
                Account account = accountRepository.findById(id).get();
                if (account.getActive()){
                    AccountDto alias = findByAlias(dto.getAlias());
                    if (alias != null){
                        if (dto.getId()!=alias.getId()){
                            throw new BankingExceptions("Ya existe una cuenta con el el ALIAS: " + dto.getAlias() + ", imposible crear la cuenta");
                        }else{
                            account.setAlias(dto.getAlias());
                        }
                    }else{
                        account.setAlias(dto.getAlias());
                    }

                    return AccountMapper.accountToDtoMap(account);
                }else{
                    throw new BankingExceptions("La cuenta no está activa, imposible actualizar");
                }
            }
        }else {
            throw new BankingExceptions("No existe una cuenta con el id + " + id + ", imposible actualizar la cuenta");
        }
    }

    public Boolean deleteAccount(Long id){
        if (accountRepository.existsById(id)){
            Account account = accountRepository.findById(id).get();
            if (account.getActive()) {
                account.setActive(false);
                account.setCbu("");
                account.setAlias("");
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
        if (cbu==""){
            return null;
        }else{
            Account account = accountRepository.findAccountByCbu(cbu);
            return account != null;
        }
    }

    public String generarCBU(){
        String cbu = "";
        while ((cbu=="")||(existsAccountByCbu(cbu))){
            cbu = "";
            for(int i = 0; i<22; i++) {
                int indice = (int) (Math.random() * 9);
                cbu += indice;
            }
        }
        return cbu;
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
