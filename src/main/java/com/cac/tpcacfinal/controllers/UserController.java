package com.cac.tpcacfinal.controllers;

import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.exceptions.BankingExceptions;
import com.cac.tpcacfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    private UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
    }

    @GetMapping(value = "/active")
    public ResponseEntity<List<UserDto>> getUsersActive(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsersActive());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    @GetMapping(value = "/username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestBody UserDto user){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserByUsername(user.getUsername(), user.getPassword()));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
        }catch(BankingExceptions e){
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> updateUserFull(@PathVariable Long id, @RequestBody UserDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateUserFull(id, user));
        }catch(BankingExceptions e){
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
        }catch(BankingExceptions e){
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUserById(id));
    }



}
