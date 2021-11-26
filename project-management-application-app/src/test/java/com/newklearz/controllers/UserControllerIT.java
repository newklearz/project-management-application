package com.newklearz.controllers;

import java.util.List;

import com.newklearz.ProjectManagementApplication;
import com.newklearz.DTO.UsersDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = ProjectManagementApplication.class)
public class UserControllerIT {
    
    @Autowired
    UserController userController;

    @Test
    public void testRetrievalOfUsers()
    {
        ResponseEntity<List<UsersDTO>> users = userController.getUsers();

        System.out.println("Printing first usename from database: " + users.getBody().get(0).getUserName());
        
    }
}
