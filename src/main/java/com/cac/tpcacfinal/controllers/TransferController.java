package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Transfer;
import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService service;

    private TransferController(TransferService transactionService){
        this.service = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transfer>> getTransactions(){

        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transfer> getTransactionById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> createdTransaction(@RequestBody TransferDto transfer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

    @PutMapping
    public ResponseEntity<TransferDto> updateTransaction(@PathVariable Long id, @RequestBody TransferDto transfer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

    @DeleteMapping (value = "/{id}")
    public String deleteTransaction(@PathVariable Long id){
        return service.deleteTransaction(id);
    }
}
