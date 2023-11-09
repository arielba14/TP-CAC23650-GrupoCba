package com.cac.tpcacfinal.entities.dtos;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //private Long id;
    private String username;
    private String password;
    private String name;
    private List<Long> idAccounts;
}
