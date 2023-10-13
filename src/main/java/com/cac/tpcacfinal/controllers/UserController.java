package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Dto.UserDto;
import com.cac.tpcacfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/users")
    public List<UserDto> getUsers(){
        return service.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public Optional<UserDto> getUserById(Long id){
        return service.getUserById(id);
    }

    @PostMapping(value = "saveUser")
    public void saveUser(UserDto user){
        service.saveUser(user);
    }

    @PutMapping(value = "updateUser")
    public void updateUser(UserDto user){
        service.updateUser(user);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(UserDto user){
        service.deleteUser(user);
    }
    @DeleteMapping(value = "deleteUser/{id}")
    public void deleteUserById(Long id){
        service.deleteUserById(id);
    }



}
