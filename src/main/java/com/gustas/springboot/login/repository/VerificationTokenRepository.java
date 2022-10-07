package com.gustas.springboot.login.repository;

import com.gustas.springboot.login.modules.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByVerificationToken(String token);

    @Modifying
    @Transactional
    @Query("UPDATE VerificationToken c SET c.confirmedAt = ?1 WHERE c.verificationToken = ?2")
    void updateConfirmedAt(LocalDateTime confirmedAt, String token);
}
