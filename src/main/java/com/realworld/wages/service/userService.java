package com.realworld.wages.service;

import com.realworld.wages.dto.userDto;
import com.realworld.wages.entities.users;
import com.realworld.wages.mapper.usersMapper;
import com.realworld.wages.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class userService {

    @Autowired
    private userRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private usersMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    public users register(users user){
//        String encodedPassword = encoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        return repo.save(user);
//    }

    public users register(userDto userDto) {
        users userEntity = userMapper.toEntity(userDto, passwordEncoder);
        return repo.save(userEntity);
    }


//    public String login(String username, String password) {
//        try {
//            Authentication authentication = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            if (authentication.isAuthenticated()) {
//                return "Login successful!";
//            } else {
//                throw new BadCredentialsException("Invalid credentials");
//            }
//
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username or password.");
//        }
//
//    }

    public String login(userDto dto) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword())
            );
            System.out.println(authentication);

            if (authentication.isAuthenticated()) {
                return "Login successful!";
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

    public userDto getUserDto(users user) {
        return userMapper.toDto(user);
    }
}
