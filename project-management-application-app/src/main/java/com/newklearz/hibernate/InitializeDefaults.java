package com.newklearz.hibernate;

import com.newklearz.DTO.AppUserRole;
import com.newklearz.repository.users.Users;
import org.springframework.stereotype.Component;

import com.newklearz.security.MyUserDetailsService;

@Component
public class InitializeDefaults
{
    private MyUserDetailsService myUserDetailsService;

    public InitializeDefaults(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
        initialize();
    }

    private void initialize()
    {
        Users admin = new Users("admin", "admin@pma", "admiN123$", AppUserRole.ADMIN);
        myUserDetailsService.signUpUser(admin);
    }
}