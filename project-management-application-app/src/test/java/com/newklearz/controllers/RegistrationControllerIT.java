package com.newklearz.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.UsersDTO;
import com.newklearz.adapters.UserAdapter;
import com.newklearz.repository.users.Users;

public class RegistrationControllerIT extends SpringBootTestEnvironment
{
    /**
     * Verify if post request registers a user
     */
    @Test
    public void testRegistrationOfUser()
    {
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", "bestadmin@gmail.com", "crocodiL123$", AppUserRole.ADMIN)));
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getUserName(), "bestadmin");
    }
}