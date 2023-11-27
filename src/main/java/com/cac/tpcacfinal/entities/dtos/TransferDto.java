package com.cac.tpcacfinal.entities.dtos;


import com.cac.tpcacfinal.entities.Account;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {
    private Long id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String description;
    private AccountDto originAccount;
    private AccountDto destinedAccount;
}
