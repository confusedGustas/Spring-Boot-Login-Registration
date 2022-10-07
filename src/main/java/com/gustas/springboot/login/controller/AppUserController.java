package com.gustas.springboot.login.controller;

import com.gustas.springboot.login.modules.AppUser;
import com.gustas.springboot.login.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/user/{id}")
    public AppUser getAppUser(@PathVariable Long id) {
        return appUserService.findById(id);
    }
}
