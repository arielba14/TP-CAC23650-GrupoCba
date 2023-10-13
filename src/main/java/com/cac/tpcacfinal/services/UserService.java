package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.Dto.UserDto;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public List<UserDto> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserDto> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void saveUser(UserDto user){
        userRepository.save(user);
    }

    public void updateUser(UserDto user){
        userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUser(UserDto user){
        userRepository.delete(user);
    }
}
