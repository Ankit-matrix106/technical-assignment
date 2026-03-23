package com.capco.onboarding.domain.port.inbound;

import java.util.List;
import java.util.Optional;

import com.capco.onboarding.adapter.outbound.entity.User;
import com.capco.onboarding.domain.model.UserVo;

public interface UserService {
	public String createUser(UserVo user);
	public List<User> getAllUsers();
	public Optional<User> getUserById(Integer id);
}
