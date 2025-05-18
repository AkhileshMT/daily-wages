package com.realworld.wages.service;

import com.realworld.wages.entities.users;
import com.realworld.wages.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public users register(users user){
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return repo.save(user);
    }


}
