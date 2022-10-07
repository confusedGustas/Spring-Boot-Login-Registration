package com.gustas.springboot.login.service;

import com.gustas.springboot.login.modules.AppUser;
import com.gustas.springboot.login.repository.AppUserRepository;
import com.gustas.springboot.login.modules.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final VerificationTokenService verificationTokenService;

    public String signUser(AppUser newAppUser) {
        boolean userExists = appUserRepository.existsByEmail(newAppUser.getEmail());

        if(userExists) {
            throw new IllegalStateException("Email already exists");
        }

        String encodedPassword = (new BCryptPasswordEncoder()).encode(newAppUser.getPassword());
        newAppUser.setPassword(encodedPassword);
        appUserRepository.save(newAppUser);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusSeconds(30), newAppUser);

        verificationTokenService.saveVerificationToken(verificationToken);

        return token;
    }

    public AppUser findById(Long Id) {
        return appUserRepository.findById(Id).orElseThrow(() ->
                new IllegalStateException("User with id " + Id + " not found"));
    }

    public void enableAppUser(String email) {
        appUserRepository.findByEmail(email);
    }
}
