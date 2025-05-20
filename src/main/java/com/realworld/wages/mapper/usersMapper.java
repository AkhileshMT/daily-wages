package com.realworld.wages.mapper;

import com.realworld.wages.dto.userDto;
import com.realworld.wages.entities.users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class usersMapper {

    public users toEntity(userDto dto, PasswordEncoder passwordEncoder){
        users user = new users();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // encode password here
        user.setType(dto.getType());
        user.setEmail(dto.getEmail());
        user.setActive(dto.getActive());
        return user;
    }


    public userDto toDto(users user){
        userDto dto = new userDto();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setType(user.getType());
        dto.setActive(user.getActive());
        return dto;
    }
}
