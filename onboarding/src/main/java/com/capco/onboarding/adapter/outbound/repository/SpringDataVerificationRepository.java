package com.capco.onboarding.adapter.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;

@Repository
public interface SpringDataVerificationRepository extends JpaRepository<AutoVerificationEntity, Integer>{
	
	Optional<AutoVerificationEntity> findByTxnNumber(String txnNumber);
	Optional<AutoVerificationEntity> findByIdentId(String identId);
}
