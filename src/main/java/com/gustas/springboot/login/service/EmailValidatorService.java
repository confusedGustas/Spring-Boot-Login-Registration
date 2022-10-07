package com.gustas.springboot.login.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailValidatorService {
    public boolean emailIsValid(String email) {
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}
