package com.example.backend.DataAccess;

import com.example.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

    boolean existsUserByUsername(String username);
}
