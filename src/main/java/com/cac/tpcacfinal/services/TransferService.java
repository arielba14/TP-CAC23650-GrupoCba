package com.cac.tpcacfinal.services;


import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.Transfer;
import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.exceptions.AccountInactiveExceptions;
import com.cac.tpcacfinal.exceptions.AccountNotFoundExceptions;
import com.cac.tpcacfinal.exceptions.BankingExceptions;
import com.cac.tpcacfinal.mappers.TransferMapper;
import com.cac.tpcacfinal.repositories.AccountRepository;
import com.cac.tpcacfinal.repositories.TransferRepository;
import com.cac.tpcacfinal.utils.AccountType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    private final DolarService dolarService;

    private TransferService(TransferRepository transferRepository, AccountRepository accountRepository, DolarService dolarService) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.dolarService = dolarService;
    }

    public List<TransferDto> getTransfers(){

        return transferRepository.findAll().stream().map(TransferMapper::transferToDtoMap).collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id){
        if(transferRepository.existsById(id)){
            return TransferMapper.transferToDtoMap(transferRepository.findById(id).get());
        }else{
            return null;
        }
    }

    /*El tipo indica que trasnsferencia se realiza
            tipo = 0 --> Compra de dólares
            tipo = 1 --> Venta de dólares
            tipo = default --> trasnferencia entre cuentas misma moneda
     */

    public boolean dataTransfers(TransferDto transfer, int tipo){
        if ((transfer.getAmount()==null)||(transfer.getAmount().compareTo(BigDecimal.ZERO)<1)){
            throw new BankingExceptions("El monto a transferir deber ser mayor que cero, imposible realizar la transacción");
        }
        if ((transfer.getOriginAccount()==null)||(transfer.getDestinedAccount()==null)) {
            throw new BankingExceptions("Debe indicar las cuentas origen y destino de la transferencia, imposible gnerar la transferencia");
        }
        if (!((accountRepository.existsById(transfer.getOriginAccount().getId())) && (accountRepository.existsById(transfer.getDestinedAccount().getId())))){
            throw new AccountNotFoundExceptions("Una de las cuentas no existe, imposible realizar la transferencia");
        }
        if (transfer.getOriginAccount().getId()==transfer.getDestinedAccount().getId()) {
            throw new BankingExceptions("Las cuentas origen y destino no pueden ser las mismas, imposible generar la transferencia");
        }
        Account origen = accountRepository.findById(transfer.getOriginAccount().getId()).get();
        Account destino = accountRepository.findById(transfer.getDestinedAccount().getId()).get();
        if (!origen.getActive()||!destino.getActive()){
            throw new AccountInactiveExceptions("Una de las cuentas no se encuentra activa, imposible realizar la transferencia");
        }
        switch (tipo){
            case 0:
                if ((origen.getType()==AccountType.CAJA_AHORRO_USD)||(origen.getType()==AccountType.CUENTA_CORRIENTE_USD)){
                    throw new BankingExceptions("Para la compra de dólares la cuenta origen debe ser un cuenta en pesos, imposible realizar la compra");
                }
                if ((destino.getType()==AccountType.CAJA_AHORRO)||(destino.getType()==AccountType.CUENTA_CORRIENTE)){
                    throw new BankingExceptions("Para la compra de dólares la cuenta destino no puede ser una cuenta de moneda nacional");
                }
                break;
            case 1:
                if ((origen.getType()==AccountType.CAJA_AHORRO)||(origen.getType()==AccountType.CUENTA_CORRIENTE)) {
                    throw new BankingExceptions("Para la venta de dólares la cuenta origen debe ser un cuenta en dólares, imposible realizar la transferencia");
                }
                if ((destino.getType()==AccountType.CAJA_AHORRO_USD)||(destino.getType()==AccountType.CUENTA_CORRIENTE_USD)){
                    throw new BankingExceptions("Para la venta de dólares la cuenta destino debe ser una cuenta de moneda nacional. Para ello debe realizar una transferencia");
                }
                break;
            default:
                if ((((origen.getType() == AccountType.CAJA_AHORRO) || (origen.getType() == AccountType.CUENTA_CORRIENTE)) && ((destino.getType() == AccountType.CAJA_AHORRO_USD) || (destino.getType() == AccountType.CUENTA_CORRIENTE_USD))) || (((origen.getType() == AccountType.CAJA_AHORRO_USD) || (origen.getType() == AccountType.CUENTA_CORRIENTE_USD)) && ((destino.getType() == AccountType.CAJA_AHORRO) || (destino.getType() == AccountType.CUENTA_CORRIENTE)))) {
                    throw new BankingExceptions("Las cuentas deben ser de la misma moneda para poder realizar la transacción, imposible realizar la transferencia");
                }
                break;
        }
        return true;
    }

    @Transactional
    public TransferDto dollarSales(TransferDto transfer){
        try{
            if (dataTransfers(transfer, 1)){
                Transfer nueva = new Transfer();
                Account origen = accountRepository.findById(transfer.getOriginAccount().getId()).get();
                Account destino = accountRepository.findById(transfer.getDestinedAccount().getId()).get();
                nueva.setDate(LocalDateTime.now());
                nueva.setOriginAccount(origen);
                nueva.setDestinedAccount(destino);
                BigDecimal cotizacion = new BigDecimal(dolarService.getDolarOficial().getCompra());
                nueva.setDescription("Operación de venta, cotización: $" + cotizacion);
                BigDecimal importeDolar = transfer.getAmount().multiply(cotizacion);
                nueva.setAmount(importeDolar);
                transferRepository.save(nueva);
                origen.setAmount(origen.getAmount().subtract(transfer.getAmount()));
                destino.setAmount(destino.getAmount().add(importeDolar));
                origen.setUpdate_at(LocalDateTime.now());
                destino.setUpdate_at(LocalDateTime.now());
                accountRepository.save(origen);
                accountRepository.save(destino);
                return TransferMapper.transferToDtoMap(nueva);
            }else{
                return null;
            }
        }catch (RuntimeException e){
            System.out.println(e.getClass().getName() + " " + e.getMessage());
            return null;
        }
    }
    @Transactional
    public TransferDto dollarPurchase(TransferDto transfer){
        try{
            if (dataTransfers(transfer, 0)){
                Transfer nueva = new Transfer();
                Account origen = accountRepository.findById(transfer.getOriginAccount().getId()).get();
                Account destino = accountRepository.findById(transfer.getDestinedAccount().getId()).get();
                nueva.setDate(LocalDateTime.now());
                nueva.setOriginAccount(origen);
                nueva.setDestinedAccount(destino);
                BigDecimal cotizacion = new BigDecimal(dolarService.getDolarSolidario().getVenta());
                nueva.setDescription("Operación de compra, cotización: $" + cotizacion);
                BigDecimal importeDolar = transfer.getAmount().divide(cotizacion,2, RoundingMode.HALF_UP);
                nueva.setAmount(importeDolar);
                transferRepository.save(nueva);
                origen.setAmount(origen.getAmount().subtract(transfer.getAmount()));
                destino.setAmount(destino.getAmount().add(importeDolar));
                origen.setUpdate_at(LocalDateTime.now());
                destino.setUpdate_at(LocalDateTime.now());
                accountRepository.save(origen);
                accountRepository.save(destino);
                return TransferMapper.transferToDtoMap(nueva);
            }else{
                return null;
            }
        }catch (RuntimeException e){
            System.out.println(e.getClass().getName() + " " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public TransferDto createTransfer(TransferDto transfer){
        try{
            if (dataTransfers(transfer, 3)){
                Transfer nueva = TransferMapper.dtoToTransferMap(transfer);
                Account origen = accountRepository.findById(transfer.getOriginAccount().getId()).get();
                Account destino = accountRepository.findById(transfer.getDestinedAccount().getId()).get();
                nueva.setDate(LocalDateTime.now());
                nueva.setAmount(transfer.getAmount());
                nueva.setOriginAccount(origen);
                nueva.setDestinedAccount(destino);
                nueva.setDescription(transfer.getDescription());
                transferRepository.save(nueva);
                origen.setAmount(origen.getAmount().subtract(transfer.getAmount()));
                destino.setAmount(destino.getAmount().add(transfer.getAmount()));
                origen.setUpdate_at(LocalDateTime.now());
                destino.setUpdate_at(LocalDateTime.now());
                accountRepository.save(origen);
                accountRepository.save(destino);
                return TransferMapper.transferToDtoMap(nueva);
            }else{
                return null;
            }
        }catch (RuntimeException e){
            System.out.println(e.getClass().getName() + " " + e.getMessage());
            return null;
        }
    }
}
