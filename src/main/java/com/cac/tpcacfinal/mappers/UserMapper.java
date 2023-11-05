package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.entities.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {
    public User dtoToUserMap(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto userToDtoMap(User user){
        UserDto dto = new UserDto();
        List<Long> accounts= new ArrayList<>();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        if (user.getAccounts() != null){
            for (Account a: user.getAccounts()){
                accounts.add(a.getId());
            }
            dto.setIdAccounts(accounts);
        }
        return dto;
    }


}
