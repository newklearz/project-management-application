package com.newklearz.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.newklearz.ProjectManagementApplication;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.UsersDTO;
import com.newklearz.adapters.UserAdapter;
import com.newklearz.repository.users.Users;

@SpringBootTest(classes = ProjectManagementApplication.class)
public class RegistrationControllerIT
{
    @Autowired
    RegistrationController registrationController;

    @Test
    public void testRegistrationOfUser()
    {
        UsersDTO user = registrationController.register(UserAdapter.toDTO(new Users("bestadmin", "bestadmin@gmail.com", "crocodiL123$", AppUserRole.ADMIN)));
        System.out.println("Registered user's email is: " + user.getEmail());
    }
}