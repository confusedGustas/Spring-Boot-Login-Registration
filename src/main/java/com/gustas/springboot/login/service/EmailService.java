package com.gustas.springboot.login.service;

import com.gustas.springboot.login.repository.EmailSenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSenderRepository {
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendVerificationEmail(String name, String email, String link) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String content =
                "Dear " + name + ",<br>"
                + "<br> Please verify your registration: "
                + "<a href=" + link + ">VERIFY</a>";

        helper.setFrom("testemail.sendonly@gmail.com");
        helper.setTo(email);
        helper.setSubject("Email Confirmation");
        helper.setText(content, true);

        mailSender.send(mimeMessage);
    }
}
