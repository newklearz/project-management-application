package com.newklearz.service;

import org.springframework.stereotype.Service;

import com.newklearz.dto.AppUserRole;
import com.newklearz.dto.UsersDTO;
import com.newklearz.repository.users.Users;
import com.newklearz.security.MyUserDetailsService;

@Service
public class RegistrationService
{
    private final MyUserDetailsService myUserDetailsService;

    public RegistrationService(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
    }

    public UsersDTO register(UsersDTO request)
    {
        return myUserDetailsService.signUpUser(
            new Users(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.ADMIN));
    }
}