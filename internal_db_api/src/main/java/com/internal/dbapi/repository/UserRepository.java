package com.internal.dbapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.internal.dbapi.dto.UserDTO;
import com.internal.dbapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    
    @Query("SELECT new com.internal.dbapi.dto.UserDTO(u.accountId, u.username) FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<UserDTO> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}