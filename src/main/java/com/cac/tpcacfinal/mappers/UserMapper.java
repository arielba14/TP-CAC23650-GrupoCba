package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.entities.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {
    public User dtoToUserMap(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setMail(dto.getMail());
        user.setDni(dto.getDni());
        user.setBirthday(dto.getBirthday());
        user.setAddress(dto.getAddress());
        user.setCrated_at(LocalDateTime.now());
        user.setUpdate_at(LocalDateTime.now());
        return user;
    }

    public UserDto userToDtoMap(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setAddress(user.getAddress());
        dto.setMail(user.getMail());
        dto.setDni(user.getDni());
        dto.setBirthday(user.getBirthday());
        dto.setCrated_at(user.getCrated_at());
        dto.setUpdate_at(user.getUpdate_at());
        List<Long> accounts= new ArrayList<>();
        if (user.getAccounts() != null){
            for (Account a: user.getAccounts()){
                accounts.add(a.getId());
            }
            dto.setIdAccounts(accounts);
        }
        return dto;
    }


}
