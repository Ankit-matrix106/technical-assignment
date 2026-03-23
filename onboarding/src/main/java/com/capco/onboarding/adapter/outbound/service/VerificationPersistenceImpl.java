package com.capco.onboarding.adapter.outbound.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capco.onboarding.adapter.outbound.entity.AutoVerificationEntity;
import com.capco.onboarding.adapter.outbound.mapper.VerificationEntityMapper;
import com.capco.onboarding.adapter.outbound.repository.SpringDataVerificationRepository;
import com.capco.onboarding.domain.model.Verification;
import com.capco.onboarding.domain.model.VerificationResponse;
import com.capco.onboarding.domain.port.outbound.VerificationPersistence;

@Component
public class VerificationPersistenceImpl implements VerificationPersistence {
	
	@Autowired
	private SpringDataVerificationRepository repository;
	
	@Autowired
	private VerificationEntityMapper entityMapper;

	@Override
	public VerificationResponse save(Verification verification) {
		
		return Optional.ofNullable(verification)
			.map(entityMapper::toEntity)
			.map(repository::save)
			.map(entityMapper::toDomain)
			.orElse(null);
			
		//AutoVerificationEntity entity = repository.save(entityMapper.toEntity(verification));
		//return entityMapper.toDomain(entity);
	}

	@Override
	public Optional<VerificationResponse> findById(int id) {
		return repository.findById(id).map(entityMapper::toDomain);
	}

	@Override
	public Optional<VerificationResponse> findByTxnNumber(String txnNumber) {
		return repository.findByTxnNumber(txnNumber).map(entityMapper::toDomain);
	}

	@Override
	public Optional<VerificationResponse> findByProviderIdentId(String identId) {
		return repository.findByIdentId(identId).map(entityMapper::toDomain);
	}

}
