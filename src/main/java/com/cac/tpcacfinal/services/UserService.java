package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.exceptions.UserExceptions;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (userRepository.existsById(id)){
            UserDto dto = UserMapper.userToDtoMap(userRepository.findById(id).get());
            dto.setPassword("*********");
            return dto;
        }else{
            throw new UserExceptions("No existe el usuario con el id " + id);
        }
    }

    public UserDto getUserByUsername(String username, String password){
       /* List<User> lista = userRepository.findAll().stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).toList();
        if (lista.size()==1){
            lista.get(0).setPassword("*********");
            return UserMapper.userToDtoMap(lista.get(0));
        }else{
            return null;
        }*/
        User user = userRepository.findByUsernamePassword(username, password);
        if (user!=null){
            return UserMapper.userToDtoMap(user);
        }else{
            throw new UserExceptions("No existe el usuario con el username = " + username + " y el password " + password);
        }
    }

    public UserDto createUser(UserDto userDto){
        User user =UserMapper.dtoToUserMap(userDto);
        User nuevo = userRepository.save(user);
        userDto = UserMapper.userToDtoMap(nuevo);
        userDto.setPassword("*********");
        return userDto;
    }

    public UserDto updateUserFull(Long id, UserDto userDto){
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            user.setUsername(userDto.getUsername());
            user.setName(userDto.getName());
            user.setDni(userDto.getDni());
            user.setAddress(userDto.getAddress());
            user.setMail(userDto.getMail());
            user.setPassword(userDto.getPassword());
            user.setBirthday(userDto.getBirthday());
            user.setUpdate_at(LocalDateTime.now());
            userRepository.save(user);
            return UserMapper.userToDtoMap(user);
        }else{
            return null;
        }
    }

    public UserDto updateUser(Long id, UserDto userDto){
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            if (userDto.getAddress()!= null){
                user.setAddress(userDto.getAddress());
            }
            if (userDto.getMail()!= null){
                user.setMail(userDto.getMail());
            }
            if (userDto.getDni()!= null){
                user.setDni(userDto.getDni());
            }
            if (userDto.getName()!= null){
                user.setName(userDto.getName());
            }
            if (userDto.getBirthday()!= null){
                user.setBirthday(userDto.getBirthday());
            }
            if (userDto.getPassword()!= null){
                user.setPassword(userDto.getPassword());
            }
            if (userDto.getUsername()!= null){
                user.setUsername(userDto.getUsername());
            }
            user.setUpdate_at(LocalDateTime.now());
            userRepository.save(user);
            return UserMapper.userToDtoMap(user);
        }else{
            return null;
        }
    }

    public String deleteUserById(Long id){
        if (userRepository.existsById(id)){
            return "El usuario con id " + id + " ha sido eliminado";
        }else{
            return "El usuario con id " + id + " no existe, por lo tanto no se puede eliminar";
        }
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

}
