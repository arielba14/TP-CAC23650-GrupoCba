package com.cac.tpcacfinal.entities;

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
    private String alias;   //alias
    private AccountType tipo;
    private Double amount;  //saldo de la cuenta
    @ManyToOne  //una cuenta es de un solo usuario
    private User user;  //el titular de la cuenta
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

}
