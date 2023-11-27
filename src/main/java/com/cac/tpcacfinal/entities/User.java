package com.cac.tpcacfinal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "usuarios")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //id de la cuenta
    @Column(name="username", unique = true)
    private String username;    //nombre de usuario
    private String password;    //password
    private String name;        //nombre del titular
    private String address;
    private String dni;
    private String birthday;
    private Boolean activo;
    private String mail;
    private LocalDateTime crated_at;
    private LocalDateTime update_at;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts; //cuentas que tiene el usuario

}
