package com.realworld.wages.service;

import com.realworld.wages.dto.userDto;
import com.realworld.wages.entities.users;
import com.realworld.wages.mapper.usersMapper;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    AuthenticationManager authManager;

    @Autowired
    private usersMapper userMapper;

    @Autowired
    private jwtUtil jwt;

    @Autowired
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public users register(userDto userDto) {
        users userEntity = userMapper.toEntity(userDto, passwordEncoder);
        return repo.save(userEntity);
    }


    public String login(userDto dto) {
        users user = repo.findByUserName(dto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return jwt.generateToken(dto.getUserName());
        } else {
            return "Invalid username or password!";
        }
    }

    public userDto getUserDto(users user) {
        return userMapper.toDto(user);
    }
}
