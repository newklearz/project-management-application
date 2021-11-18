package com.newklearz.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@Api("Ui")
public interface UiResource
{
    String USER_COMMON_PREFIX = "/users";

    @RequestMapping(USER_COMMON_PREFIX)
    Principal user(Principal user);
}