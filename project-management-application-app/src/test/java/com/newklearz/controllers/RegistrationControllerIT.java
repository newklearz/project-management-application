package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getEmail;
import static com.newklearz.controllers.Utils.getPassword;
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
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", getEmail(), getPassword(), AppUserRole.ADMIN)));
        assertNotNull(user);
        assertEquals(user.getUserName(), "bestadmin");
    }
}