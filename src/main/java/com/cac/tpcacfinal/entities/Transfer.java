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
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //identificación de la transacción
    private Date date;  //fecha
    private Double amount;  //importe de la transferencia
    private String description; //descricpción
    @ManyToOne  //una transferencia tiene una sola cuenta origen, una cuenta tiene varias transacciones
    private Account originAccount;
    @ManyToOne  //una transacción tiene una sola cuenta destino, una cuenta tiene varias transacciones
    private Account destinedAccount;
}
