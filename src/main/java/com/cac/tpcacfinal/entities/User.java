package com.cac.tpcacfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //id de la cuenta
    private String username;    //nombre de usuario
    private String password;    //password
    private String name;        //nombre del titular
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts; //cuentas que tiene el usuario

}
