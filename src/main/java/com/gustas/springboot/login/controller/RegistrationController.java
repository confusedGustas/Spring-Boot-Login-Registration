package com.gustas.springboot.login.controller;

import com.gustas.springboot.login.modules.AppUser;
import com.gustas.springboot.login.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
@Validated
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public void newAppUser(@Valid @RequestBody AppUser newAppUser) throws MessagingException {
        registrationService.registerNewUser(newAppUser);
    }

    @GetMapping("/registration/verify")
    public void verifyToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
    }
}
