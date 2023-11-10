package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.dtos.TransferDto;
import com.cac.tpcacfinal.entities.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {
    public Transfer dtoToTransferMap(TransferDto dto){
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setDescription(dto.getDescription());
        transfer.setDate(dto.getDate());
        transfer.setDestinedAccount(AccountMapper.dtoToAccountMap(dto.getDestinedAccount()));
        transfer.setOriginAccount(AccountMapper.dtoToAccountMap(dto.getOriginAccount()));
        return transfer;
    }

    public TransferDto transferToDtoMap(Transfer transfer){
        TransferDto dto = new TransferDto();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setDate(transfer.getDate());
        dto.setDescription(transfer.getDescription());
        dto.setOriginAccount(AccountMapper.accountToDtoMap(transfer.getOriginAccount()));
        dto.setDestinedAccount(AccountMapper.accountToDtoMap(transfer.getDestinedAccount()));
        return dto;
    }
}
