package com.cac.tpcacfinal.services;

import com.cac.tpcacfinal.entities.User;
import com.cac.tpcacfinal.entities.dtos.UserDto;
import com.cac.tpcacfinal.exceptions.BankingExceptions;
import com.cac.tpcacfinal.mappers.UserMapper;
import com.cac.tpcacfinal.repositories.AccountRepository;
import com.cac.tpcacfinal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository userRepository;


    private UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    public List<UserDto> getUsers(){
        List<UserDto> lista =  userRepository.findAll().stream().map(user -> UserMapper.userToDtoMap(user)).collect(Collectors.toList());
        lista.forEach(user -> user.setPassword("*********"));
        return lista;
    }

    public List<UserDto> getUsersActive(){
        List<UserDto> lista =  userRepository.findAll().stream().filter(User::getActivo).map(user -> UserMapper.userToDtoMap(user)).collect(Collectors.toList());
        lista.forEach(user -> user.setPassword("*********"));
        return lista;
    }

    public UserDto getUserById(Long id){
        if (userRepository.existsById(id)){
            UserDto dto = UserMapper.userToDtoMap(userRepository.findById(id).get());
            dto.setPassword("*********");
            return dto;
        }else{
            return null;
        }
    }

    public UserDto getUserByUsername(String username, String password){
        UserDto user = findByUsernameAndPassword(username, password);
        if (user!=null){
            return user;
        }else{
            return null;
        }
    }

    public UserDto createUser(UserDto userDto){
        if ((userDto.getUsername()==null) || (userDto.getDni()== null) || (userDto.getName()==null)){
            throw new BankingExceptions("El nombre de usuario, dni y nombre no pueden ser vacios. Imposible crear el usuario");
        }else{
            if (!existsByUsername(userDto.getUsername())){
                if (!existsByDni(userDto.getDni())){
                    User user =UserMapper.dtoToUserMap(userDto);
                    user.setCrated_at(LocalDateTime.now());
                    user.setUpdate_at(LocalDateTime.now());
                    user.setActivo(true);
                    User nuevo = userRepository.save(user);
                    userDto = UserMapper.userToDtoMap(nuevo);
                    userDto.setPassword("*********");
                    return userDto;
                }else{
                    throw new BankingExceptions("Ya existe un usuario con el dni " + userDto.getDni() + ", no se puede crear el usuario");
                }
            }else{
                throw new BankingExceptions("Ya existe un usuario con el username " + userDto.getUsername() + ", no se puede crear el usuario");
            }
        }
    }

    public UserDto updateUserFull(Long id, UserDto userDto){
        if (userRepository.existsById(id)){
            if ((userDto.getUsername()==null) || (userDto.getDni()== null) || (userDto.getName()==null)){
                throw new BankingExceptions("El nombre de usuario, dni y nombre no pueden ser vacios. Imposible actualizar el usuario");
            }else {
                User user = userRepository.findById(id).get();
                if (user.getActivo()){
                    User userName = userRepository.findByUsername(userDto.getUsername());
                    if (userName != null) {
                        if (user.getId() != userName.getId()) {
                            throw new BankingExceptions("Ya existe un usuario con el username " + userDto.getUsername() + ", imposible realizar la actualización del username");
                        } else {
                            user.setUsername(userDto.getUsername());
                        }
                    } else {
                        user.setUsername(userDto.getUsername());
                    }
                    User userDni = userRepository.findByDni(userDto.getDni());
                    if (userDni != null){
                        if (user.getId()!=userDni.getId()){
                            throw new BankingExceptions("Ya existe un usuario con el dni " + userDto.getDni() + ", imposible realizar la actualización del username");
                        }
                    }

                    user.setName(userDto.getName());
                    user.setAddress(userDto.getAddress());
                    user.setMail(userDto.getMail());
                    user.setPassword(userDto.getPassword());
                    user.setBirthday(userDto.getBirthday());
                    user.setUpdate_at(LocalDateTime.now());
                    userRepository.save(user);
                    return UserMapper.userToDtoMap(user);
                }else{
                    throw new BankingExceptions("El usuario se encuentra inactivo, imposible actualizar");
                }
            }
        }else{
            throw new BankingExceptions("No existe el usuario con el id " + id + ", no se puede actualizar el usuario");
        }
    }

    public UserDto updateUser(Long id, UserDto userDto){
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            if (user.getActivo()){
                if (userDto.getUsername()!=null){
                    User userName = userRepository.findByUsername(userDto.getUsername());
                    if (userName != null){
                        if (user.getId()!=userName.getId()){
                            throw new BankingExceptions("Ya existe un usuario con el username " + userDto.getUsername() + ", imposible realizar la actualización del username");
                        }else{
                            user.setUsername(userDto.getUsername());
                        }
                    }else{
                        user.setUsername(userDto.getUsername());
                    }
                    User userDni = userRepository.findByDni(userDto.getDni());
                    if (userDni != null){
                        if (user.getId()!=userDni.getId()){
                            throw new BankingExceptions("Ya existe un usuario con el dni " + userDto.getDni() + ", imposible realizar la actualización del username");
                        }
                    }

                }
                if (userDto.getAddress()!= null){
                    user.setAddress(userDto.getAddress());
                }
                if (userDto.getMail()!= null){
                    user.setMail(userDto.getMail());
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
                throw new BankingExceptions("El usuario se encuentra inactivo, imposible actualizar");
            }
        }else{
            throw new BankingExceptions("No existe el usuario con el id " + id + ", no se puede actualizar el usuario");
        }
    }

    public boolean deleteUserById(Long id){

        if (userRepository.existsById(id)){
            User del = userRepository.findById(id).get();
            if (del.getAccounts().stream().filter(cuentas -> cuentas.getActive()).toList().size()>0){
                return false;
            }else{
                del.setActivo(false);
                del.setUsername("inactivo"+id);
                userRepository.save(del);
                return true;
            }
        }else{
            return false;
        }
    }

    public UserDto findByUsernameAndPassword(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null){
            if (user.getPassword().equals(password)){
                return UserMapper.userToDtoMap(user);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    public boolean existsByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public boolean existsByDni(String dni){
        User user = userRepository.findByDni(dni);
        return user != null;
    }

}
