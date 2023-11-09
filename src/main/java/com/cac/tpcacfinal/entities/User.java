package com.cac.tpcacfinal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //id de la cuenta
    @Column(name="username", unique = true)
    private String username;    //nombre de usuario
    private String password;    //password
    private String name;        //nombre del titular
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts; //cuentas que tiene el usuario

}
