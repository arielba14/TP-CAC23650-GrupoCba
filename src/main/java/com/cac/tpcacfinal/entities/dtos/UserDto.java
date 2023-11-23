package com.cac.tpcacfinal.entities.dtos;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDto {

    public UserDto() {
    }

    private Long id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String dni;
    private String birthday;
    private Boolean activo;
    private String mail;
    private LocalDateTime crated_at;
    private LocalDateTime update_at;
    private List<Long> idAccounts;

}
