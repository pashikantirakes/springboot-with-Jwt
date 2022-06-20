package com.example.done.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.done.Response.UserResponse;
import com.example.done.entity.User;
import com.example.done.request.UserRequest;
import com.example.done.serice.IUserService;
import com.example.done.util.Jwt;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired(required=false)
	private IUserService service;
	
	@Autowired
	private Jwt jwtUtil;
	
	@Autowired(required=false)
	private AuthenticationManager authManager;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		Integer id=service.saveUser(user);
		return ResponseEntity.ok("User created " + id);
		
	}
	
	@PostMapping("/home")
	public ResponseEntity<String> saveAfter(){
		return ResponseEntity.ok("WELCOME TO SECURED RESOURCE AFTER LOGIN");
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request){
		authManager.authenticate(
           new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
				);
		String token=jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"Rakesh"));
		
	}
	

}
