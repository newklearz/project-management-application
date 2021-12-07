package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.UsersDTO;
import com.newklearz.adapters.UserAdapter;
import com.newklearz.repository.users.Users;

public class RegistrationControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRegistrationOfUser()
    {
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", Utils.getAlphaNumericString(), "crocodiL123$", AppUserRole.ADMIN)));
        assertNotNull(user);
        assertEquals(user.getUserName(), "bestadmin");
    }
}