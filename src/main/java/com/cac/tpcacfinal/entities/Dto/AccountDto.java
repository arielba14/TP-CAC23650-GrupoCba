package com.cac.tpcacfinal.entities.Dto;


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
    private String tipo;
    private Double amount;
    private UserDto user;
    private List<Long> transactions;
}
