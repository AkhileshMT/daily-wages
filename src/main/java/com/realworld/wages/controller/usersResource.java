package com.realworld.wages.controller;

import com.realworld.wages.entities.users;
import com.realworld.wages.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class usersResource {

	@Autowired
	private userService service;
	
	@GetMapping("/hello")
	public String greet() {
		return "Welcome Akhilesh";
	}

	@PostMapping("/register")
	public ResponseEntity<users> register(@RequestBody users user) {
		users savedUser = service.register(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@PostMapping("/login")
	public String login(@RequestBody users user){
		System.out.println(user);
		return  "Success";
	}
}
