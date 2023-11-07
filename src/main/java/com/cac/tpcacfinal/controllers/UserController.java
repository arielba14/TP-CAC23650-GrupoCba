package com.cac.tpcacfinal.controllers;


import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(value = "/users")
    public List<UserDto> getUsers(){
        return service.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PostMapping(value = "insertUser")
    public void saveUser(@RequestBody User user){
        service.saveUser(user);
    }

    @PutMapping(value = "updateUser/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        User userId = UserMapper.dtoToUserMap(service.getUserById(id));
        userId.setUsername(user.getUsername());
        userId.setPassword(user.getPassword());
        userId.setName(user.getName());
        userId.setAccounts(user.getAccounts());
        service.saveUser(userId);
    }

    @DeleteMapping(value = "deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id){
        service.deleteUserById(id);
    }



}
