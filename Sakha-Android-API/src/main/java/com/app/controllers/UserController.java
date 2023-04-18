package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
import com.app.helper.LoginRequestBody;
import com.app.services.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(path="/saveUser",consumes="*")
	public ResponseEntity<User> saveUser(@RequestBody User user)
	{
		if(userService.checkEmailExist(user.getUserEmail()))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping(path="/authenticateLogin",consumes="*")
	public ResponseEntity<User> getUser(@RequestBody LoginRequestBody loginCredentials)
	{
		
		System.out.println("User login checking...."+loginCredentials);
		User user=userService.getUserByEmailAndPassword(loginCredentials.getUserEmail(),loginCredentials.getPassword());
		if(user==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping(path="/getCurrentUser",consumes="*")
	public ResponseEntity<User> getCurrentUser(@RequestBody int userId)
	{
		User user=userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping(path="/checkEmailExist",consumes="*")
	public boolean checkEmailExist(@RequestBody String userEmail)
	{
		return userService.checkEmailExist(userEmail);
	}
}
