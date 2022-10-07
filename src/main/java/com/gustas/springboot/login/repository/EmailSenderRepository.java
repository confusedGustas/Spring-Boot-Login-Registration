package com.gustas.springboot.login.repository;

import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;

@Repository
public interface EmailSenderRepository {
    void sendVerificationEmail(String name, String email, String link) throws MessagingException;
}
