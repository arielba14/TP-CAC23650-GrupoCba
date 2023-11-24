package com.cac.tpcacfinal.entities.dtos;


import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String cbu;
    private String alias;
    private AccountType type;
    private BigDecimal amount;
    private Boolean active;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    private UserDto user;

}
