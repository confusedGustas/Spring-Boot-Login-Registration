package com.gustas.springboot.login.service;

import com.gustas.springboot.login.modules.AppUser;
import com.gustas.springboot.login.modules.VerificationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidatorService emailValidator;
    private final EmailService emailService;
    private final VerificationTokenService verificationTokenService;

    public void registerNewUser(AppUser newAppUser) throws MessagingException {
        boolean emailIsValid = emailValidator.emailIsValid(newAppUser.getEmail());

        if (!emailIsValid) {
            throw new IllegalStateException("Email is not valid");
        }

        String token = appUserService.signUser(newAppUser);

        String link = "http://localhost:8080/api/v1/registration/verify?token=" + token;
        emailService.sendVerificationEmail(newAppUser.getName(), newAppUser.getEmail(), link);
    }

    public void confirmToken(String token) {
        VerificationToken verificationToken = verificationTokenService.getToken(token).orElseThrow(() ->
                new IllegalStateException("Token not found"));

        if(verificationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = verificationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is expired");
        }

        verificationTokenService.updateConfirmedAt(LocalDateTime.now(), token);
        appUserService.enableAppUser(verificationToken.getAppUser().getEmail());
    }
}