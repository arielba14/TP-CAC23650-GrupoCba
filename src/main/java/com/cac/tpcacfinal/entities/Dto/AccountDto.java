package com.cac.tpcacfinal.entities.Dto;


import com.cac.tpcacfinal.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String alias;
    private AccountType tipo;
    private Double amount;
    private UserDto user;
    private List<Long> transactions;
}
