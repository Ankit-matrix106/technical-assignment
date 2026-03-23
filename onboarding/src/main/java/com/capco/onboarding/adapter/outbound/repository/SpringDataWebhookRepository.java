package com.capco.onboarding.adapter.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capco.onboarding.adapter.outbound.entity.VerificationStatusEntity;

@Repository
public interface SpringDataWebhookRepository extends JpaRepository<VerificationStatusEntity, Integer>{

}
