package com.capco.onboarding.adapter.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.onboarding.adapter.outbound.entity.IdempotencyKeyEntity;

public interface IdempotencyKeyRepository extends JpaRepository<IdempotencyKeyEntity, Long>{
	
	Optional<IdempotencyKeyEntity> findByIdempotencyKey(String idempotencyKey);

}
