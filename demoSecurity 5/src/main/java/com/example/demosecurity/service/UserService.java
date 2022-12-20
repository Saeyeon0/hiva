package com.example.demosecurity.service;

import com.example.demosecurity.models.User;
import com.example.demosecurity.models.repo.UserRepo;
import com.example.demosecurity.security.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.saveAndFlush(user);
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(String.format("User with email %s not found",username));

        return new UserDAO(user);
    }
}
