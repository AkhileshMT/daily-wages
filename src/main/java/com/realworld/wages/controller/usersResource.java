package com.realworld.wages.controller;

import com.realworld.wages.dto.userDto;
import com.realworld.wages.entities.users;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.service.userService;

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
	
	@GetMapping("/hello")
	public String greet() {
		return "Welcome Akhilesh";
	}

//	@PostMapping("/register")
//	public ResponseEntity<users> register(@RequestBody users user) {
//		users savedUser = service.register(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
//	}

	@PostMapping("/register")
	public ResponseEntity<userDto> register(@RequestBody userDto dto){
		users savedUser = service.register(dto);
		userDto responseDto = service.getUserDto(savedUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

//	@PostMapping("/login")
//	public String login(@RequestBody users user){
//		System.out.println(user);
//		return  service.verfyUser(user);
//	}

//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody users user){
//		try{
//			String result = service.login(user.getUserName(), user.getPassword());
//			System.out.println(result);
//			return ResponseEntity.ok(result);
//
//		}catch (BadCredentialsException ex){
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//		}
//	}

//	@PostMapping("/login")
//	public String login(@RequestBody userDto loginDto) {
//		System.out.println(loginDto);
//		return service.login(loginDto);
//	}

//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody userDto loginDto) {
//		String response = service.login(loginDto);
//		return ResponseEntity.ok(response);
//	}

	@PostMapping("/login")
	public String manualLogin(@RequestBody userDto dto) {
		users user = repo.findByUserName(dto.getUserName())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			return "Login successful!";
		} else {
			return "Invalid username or password!";
		}
	}
}
