package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(user -> UserMapper.userToDtoMap(user)).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            user.setPassword("*********");
            return UserMapper.userToDtoMap(user);
        }else{
            return null;
        }
    }

    public UserDto createUser(UserDto user){
        userRepository.save(UserMapper.dtoToUserMap(user));
        UserDto nuevo = user;
        nuevo.setPassword("*********");
        return user;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
