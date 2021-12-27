package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.COMPLEX_PASSWORD;
import static com.newklearz.controllers.Utils.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.adapters.UserAdapter;
import com.newklearz.dto.AppUserRole;
import com.newklearz.dto.UsersDTO;
import com.newklearz.repository.users.Users;

public class RegistrationControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRegistrationOfUser()
    {
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", EMAIL, COMPLEX_PASSWORD, AppUserRole.ADMIN)));
        assertNotNull(user);
        assertEquals(user.getUserName(), "bestadmin");
    }
}