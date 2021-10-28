package com.newklearz.projectmanagement.registration;

import org.springframework.stereotype.Service;

import com.newklearz.projectmanagement.repository.users.AppUserRole;
import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.security.MyUserDetailsService;

@Service
public class RegistrationService
{
    private final MyUserDetailsService myUserDetailsService;

    public RegistrationService(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
    }

    public String register(RegistrationRequest request)
    {
        return myUserDetailsService.signUpUser(
            new Users(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER));

    }
}
