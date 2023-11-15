package com.cac.tpcacfinal.entities;

import com.cac.tpcacfinal.utils.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "cuentas")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //identificación de la cuenta
    private Long number;    //CBU
    private String alias;   //alias
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Boolean active;
    private Double amount;  //saldo de la cuenta
    @ManyToOne  //una cuenta es de un solo usuario
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;  //el titular de la cuenta

}
