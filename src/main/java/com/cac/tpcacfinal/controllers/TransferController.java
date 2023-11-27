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
    public ResponseEntity<List<TransferDto>> getTranfers(){

        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDto> createdTransfer(@RequestBody TransferDto transfer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

    @PutMapping
    public ResponseEntity<TransferDto> updateTranfer(@PathVariable Long id, @RequestBody TransferDto transfer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

    @DeleteMapping (value = "/{id}")
    public boolean deleteTranfer(@PathVariable Long id){
        return false;
    }
}
