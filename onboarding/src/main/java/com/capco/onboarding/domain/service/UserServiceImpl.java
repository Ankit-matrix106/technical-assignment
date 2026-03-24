package com.capco.onboarding.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capco.onboarding.adapter.outbound.entity.User;
import com.capco.onboarding.adapter.outbound.repository.UserRepository;
import com.capco.onboarding.domain.model.UserVo;
import com.capco.onboarding.domain.port.inbound.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String createUser(UserVo user) {
		
		if (userRepository.existsByUsername(user.username())) {
            return "Error: Username is already taken!";
        }
		
		User u = User.builder().username(user.username())
							   .password(passwordEncoder.encode(user.password()))
							   .build();
		User createdUser = userRepository.save(u);
		return "User Created";
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		
		return userRepository.findById(id);
	}
	

}
