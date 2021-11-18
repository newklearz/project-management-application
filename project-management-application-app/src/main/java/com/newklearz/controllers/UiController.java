package com.newklearz.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UiController implements UiResource
{
    @Override
    public Principal user(Principal user)
    {
        return user;
    }
}