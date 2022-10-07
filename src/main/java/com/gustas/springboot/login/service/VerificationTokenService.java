package com.gustas.springboot.login.service;

import com.gustas.springboot.login.repository.VerificationTokenRepository;
import com.gustas.springboot.login.modules.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public void saveVerificationToken(VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }

    public Optional<VerificationToken> getToken(String token) {
        return verificationTokenRepository.findByVerificationToken(token);
    }

    public void updateConfirmedAt(LocalDateTime confirmedAt, String token) {
        verificationTokenRepository.updateConfirmedAt(confirmedAt, token);
    }
}
