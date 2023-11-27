package com.cac.tpcacfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table (name = "transferencias")
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //identificación de la transacción
    private LocalDateTime date;  //fecha
    private BigDecimal amount;  //importe de la transferencia
    private String description; //descricpción
    @ManyToOne  //una transferencia tiene una sola cuenta origen, una cuenta tiene varias transferencias
    @JoinColumn(name = "cuenta_origen_id", referencedColumnName = "id")
    private Account originAccount;
    @ManyToOne  //una transacción tiene una sola cuenta destino, una cuenta tiene varias transferencias
    @JoinColumn(name="cuenta_destino_id", referencedColumnName = "id")
    private Account destinedAccount;
}
