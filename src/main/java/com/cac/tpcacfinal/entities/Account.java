package com.cac.tpcacfinal.entities;

import com.cac.tpcacfinal.utils.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "cuentas")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //identificación de la cuenta
    @Column(name="cbu", unique = true)
    private String cbu;    //CBU
    @Column(name="alias", unique = true)
    private String alias;   //alias
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Boolean active;
    private BigDecimal amount;  //saldo de la cuenta
    private LocalDateTime created_at;
    private LocalDateTime update_at;
    @ManyToOne  //una cuenta es de un solo usuario
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;  //el titular de la cuenta

    //ver....
   /* @OneToMany(mappedBy = "transferOr", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> origen; //cuentas que tiene el usuario
    @OneToMany(mappedBy = "transferDes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> destino; //cuentas que tiene el usuario*/

}
