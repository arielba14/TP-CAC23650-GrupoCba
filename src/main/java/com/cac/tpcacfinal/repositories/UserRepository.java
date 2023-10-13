package com.cac.tpcacfinal.repositories;


import com.cac.tpcacfinal.entities.Dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
}
