package com.newklearz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newklearz.dto.UsersDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Registration")
public interface RegistrationResource
{
    String REGISTER = "/api/v1/registration";

    @ApiOperation("Sign up a user")
    @ResponseBody
    @PostMapping(REGISTER)
    UsersDTO register(@RequestBody UsersDTO request);
}