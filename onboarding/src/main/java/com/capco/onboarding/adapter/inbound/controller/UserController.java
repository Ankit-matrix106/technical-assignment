package com.capco.onboarding.adapter.inbound.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.onboarding.adapter.inbound.model.request.UserRequest;
import com.capco.onboarding.adapter.outbound.entity.User;
import com.capco.onboarding.domain.model.UserVo;
import com.capco.onboarding.domain.port.inbound.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/signup")
	public String createUser(@RequestBody UserRequest request) {
		UserVo user = new UserVo(request.username(), request.password());
		return userService.createUser(user);
	}
}
