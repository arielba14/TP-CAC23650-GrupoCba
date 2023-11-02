package com.cac.tpcacfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table (name = "transacciones")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //identificación de la transacción
    private TransactionType type;   //tipo, por el momento si es un débito o un crédito
    private Date date;  //fecha
    private Double amount;  //importe de la transacción
    private String description; //descricpción
    @ManyToOne  //una transacción tiene una sola cuenta, una cuenta tiene varias transacciones
    private Account account;
}
