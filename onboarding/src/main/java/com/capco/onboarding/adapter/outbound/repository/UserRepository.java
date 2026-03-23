package com.capco.onboarding.adapter.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capco.onboarding.adapter.outbound.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
    boolean existsByUsername(String username);
    
}
