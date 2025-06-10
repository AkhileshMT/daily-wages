package com.realworld.wages.controller;

import com.realworld.wages.dto.userDto;
import com.realworld.wages.entities.users;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.service.userService;

import com.realworld.wages.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class usersResource {

	@Autowired
	private userService service;

	@Autowired
	private userRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private jwtUtil jwt;
	
	@GetMapping("/hello")
	public String greet() {
		return "Welcome Akhilesh";
	}


	@PostMapping("/register")
	public ResponseEntity<userDto> register(@RequestBody userDto dto){
		users savedUser = service.register(dto);
		userDto responseDto = service.getUserDto(savedUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}


	@PostMapping("/login")
	public String manualLogin(@RequestBody userDto dto) {
		return service.login(dto);

	}
}
