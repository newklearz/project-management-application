package com.newklearz.registration;

import org.springframework.stereotype.Service;

import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.UsersDTO;
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

    public UsersDTO register(RegistrationRequest request)
    {
        return myUserDetailsService.signUpUser(
            new Users(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.ADMIN));
    }
}
