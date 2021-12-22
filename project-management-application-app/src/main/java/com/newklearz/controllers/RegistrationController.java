package com.newklearz.controllers;

import com.newklearz.dto.UsersDTO;
import com.newklearz.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController implements RegistrationResource
{
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }

    @PostMapping
    public UsersDTO register(@RequestBody UsersDTO request)
    {
        return registrationService.register(request);
    }
}