package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers(){
        List<UserDto> lista =  userRepository.findAll().stream().map(user -> UserMapper.userToDtoMap(user)).collect(Collectors.toList());
        lista.forEach(user -> user.setPassword("*********"));
        return lista;
    }

    public UserDto getUserById(Long id){
        UserDto dto = UserMapper.userToDtoMap(userRepository.findById(id).get());
        dto.setPassword("*********");
        return dto;
    }

    public UserDto createUser(UserDto userDto){
        User user =UserMapper.dtoToUserMap(userDto);
        User nuevo = userRepository.save(user);
        userDto = UserMapper.userToDtoMap(nuevo);
        userDto.setPassword("*********");
        return userDto;
    }

    public UserDto updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).get();
       //hacer
        return userDto;
    }

    public String deleteUserById(Long id){
        if (userRepository.existsById(id)){
            return "El usuario con id " + id + " ha sido eliminado";
        }else{
            return "El usuario con id " + id + " no existe, por lo tanto no se puede eliminar";
        }
    }

}
