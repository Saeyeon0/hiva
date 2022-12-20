package com.example.demosecurity.models.repo;

import com.example.demosecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByEmail(String email);
}
