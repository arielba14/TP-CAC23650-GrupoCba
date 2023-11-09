package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    @PostMapping(value = "user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @PutMapping(value = "user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto user){
        UserDto userId = service.getUserById(id);
        userId.setUsername(user.getUsername());
        //userId.setPassword(user.getPassword());
        userId.setName(user.getName());
        service.createUser(userId);
    }

    @DeleteMapping(value = "user/{id}")
    public void deleteUserById(@PathVariable Long id){
        service.deleteUserById(id);
    }



}
