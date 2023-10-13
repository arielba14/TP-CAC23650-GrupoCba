package com.cac.tpcacfinal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String nombre;


}
