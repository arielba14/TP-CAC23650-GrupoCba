package com.cac.tpcacfinal.repositories;



import com.cac.tpcacfinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByUsername(String username);
    public User findByUsernamePassword(String username, String password);
}
