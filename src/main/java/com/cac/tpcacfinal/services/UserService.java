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
        return UserMapper.userToDtoMap(userRepository.findById(id).orElse(null));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
