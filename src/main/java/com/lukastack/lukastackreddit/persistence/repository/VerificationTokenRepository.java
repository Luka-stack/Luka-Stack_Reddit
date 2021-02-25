package com.lukastack.lukastackreddit.persistence.repository;

import com.lukastack.lukastackreddit.persistence.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {

    Optional<VerificationTokenEntity> findByToken(String token);
}
