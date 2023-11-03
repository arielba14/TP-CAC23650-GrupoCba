package com.cac.tpcacfinal.entities.Dto;


import com.cac.tpcacfinal.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private TransactionType type;
    private Date date;
    private Double amount;
    private String description;
    private AccountDto account;
}