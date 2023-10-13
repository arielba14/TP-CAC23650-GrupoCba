package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.Dto.UserDto;
import com.cac.tpcacfinal.entities.User;
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
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PostMapping(value = "saveUser")
    public void saveUser(@RequestBody User user){
        service.saveUser(user);
    }

    @PutMapping(value = "updateUser/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        User userId = service.getUserById(id);
        userId.setUsername(user.getUsername());
        userId.setPassword(user.getPassword());
        userId.setName(user.getName());
        service.saveUser(userId);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(User user){
        service.deleteUser(user);
    }
    @DeleteMapping(value = "deleteUser/{id}")
    public void deleteUserById(Long id){
        service.deleteUserById(id);
    }



}
