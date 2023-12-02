package com.cac.tpcacfinal.mappers;

import com.cac.tpcacfinal.entities.Account;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.entities.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public User dtoToUserMap(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setMail(dto.getMail());
        user.setDni(dto.getDni());
        user.setBirthday(dto.getBirthday());
        user.setActivo(dto.getActivo());
        user.setAddress(dto.getAddress());
        user.setCreated_at(dto.getCreated_at());
        user.setUpdate_at(dto.getUpdate_at());
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
        dto.setActivo(user.getActivo());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdate_at(user.getUpdate_at());
        if (user.getAccounts()!=null) {
            dto.setIdAccounts(user.getAccounts().stream().map(account -> account.getId()).collect(Collectors.toList()));
        }
        return dto;
    }


}
