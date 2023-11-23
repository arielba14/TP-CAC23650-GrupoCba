package com.cac.tpcacfinal.services;


import com.cac.tpcacfinal.entities.Transfer;
import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.mappers.TransferMapper;
import com.cac.tpcacfinal.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public List<Transfer> getTransfers(){

        return transferRepository.findAll();
    }

    public Transfer getTransferById(Long id){
        return transferRepository.findById(id).get();
    }

    public TransferDto createTransfer(TransferDto transfer){
        Transfer nueva = TransferMapper.dtoToTransferMap(transfer);
        transferRepository.save(nueva);
        return TransferMapper.transferToDtoMap(nueva);
    }

    public String deleteTransaction(Long id){

        transferRepository.deleteById(id);
        return "";
    }
}
