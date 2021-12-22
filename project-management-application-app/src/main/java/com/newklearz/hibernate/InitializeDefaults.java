package com.newklearz.hibernate;

import org.springframework.stereotype.Component;

import com.newklearz.dto.AppUserRole;
import com.newklearz.repository.users.Users;
import com.newklearz.security.MyUserDetailsService;

@Component
public class InitializeDefaults
{
    private final MyUserDetailsService myUserDetailsService;

    public InitializeDefaults(MyUserDetailsService myUserDetailsService)
    {
        this.myUserDetailsService = myUserDetailsService;
        initialize();
    }

    private void initialize()
    {
        Users admin = new Users("admin", "admin@pma", "admiN123$", AppUserRole.ADMIN);
        Users user1 = new Users("costin", "costin@pma", "admiN123$", AppUserRole.USER);
        Users user2 = new Users("florin", "florin@pma", "admiN123$", AppUserRole.USER);
        Users user3 = new Users("ioan", "ioan@pma", "admiN123$", AppUserRole.USER);
        Users user4 = new Users("sergiu", "sergiu@pma", "admiN123$", AppUserRole.USER);
        Users user5 = new Users("marian", "marian@pma", "admiN123$", AppUserRole.USER);
        myUserDetailsService.signUpUser(admin);
        myUserDetailsService.signUpUser(user1);
        myUserDetailsService.signUpUser(user2);
        myUserDetailsService.signUpUser(user3);
        myUserDetailsService.signUpUser(user4);
        myUserDetailsService.signUpUser(user5);
    }
}