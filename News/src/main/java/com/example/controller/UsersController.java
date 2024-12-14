package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Users;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Users> addUser(@Valid @RequestBody Users user){
		return new ResponseEntity<Users>(userService.addUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/GetById/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable int userId){
		return new ResponseEntity<Users>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Users> updateUser(@PathVariable int userId, @RequestBody Users user){
		return new ResponseEntity<Users>(userService.updateUser(userId, user),HttpStatus.OK);
	}
	
	
	@PutMapping("/updateUserSubscriptionStatus/{userId}/{subscriptionStatus}")    
	public ResponseEntity<Users> updateSubscriptionStatus(@PathVariable int userId, @PathVariable String subscriptionStatus){
		return new ResponseEntity<Users>(userService.updateSubscriptionStatus(userId, subscriptionStatus),HttpStatus.OK);
	}
	
	
	@PostMapping("/login")    //login
	public ResponseEntity<Users> loginStudent(@RequestBody Users users) {
		return new ResponseEntity<Users>(userService.loginUser(users),HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers(){
		return (userService.GetAllUsers());
	}
	
	@GetMapping("/{userId}/subscription-status")
	public ResponseEntity<String> getSubscriptionStatus(@PathVariable int userId) {
		String subscriptionStatus = userService.getSubscriptionStatus(userId);
		return ResponseEntity.ok(subscriptionStatus);
	}
}
