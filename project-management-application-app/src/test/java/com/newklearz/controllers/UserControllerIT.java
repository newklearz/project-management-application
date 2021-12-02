package com.newklearz.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.newklearz.ProjectManagementApplication;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;
import com.newklearz.DTO.UsersDTO;

@SpringBootTest(classes = ProjectManagementApplication.class)
public class UserControllerIT
{
    @Autowired
    UserController userController;
    UsersDTO usersDTO;
    TicketDTO ticketDTO;
    TicketDetailsDTO ticketDetailsDTO;

    @BeforeEach
    public void setUp()
    {
        ticketDetailsDTO = new TicketDetailsDTO(null, "Avem niste probleme urgente", "Dragonii albstri", "Ultimul");
        ticketDTO = new TicketDTO(null, "test", "bug", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee",
            ticketDetailsDTO);
        usersDTO = new UsersDTO(7, "hero", "heroz@gmail.com", "admiN123$", AppUserRole.ADMIN,
            Collections.singletonList(ticketDTO), true);
    }

    @Test
    public void testRetrievalOfUsers()
    {
        ResponseEntity<List<UsersDTO>> users = userController.getUsers();
        for (int i = 0; i < users.getBody().size(); i++)
        {
            System.out.print("User " + (1 + i) + ": " + users.getBody().get(i).getUserName() + " \n");
        }
    }

    @Test
    public void testRetrievalOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(1);
        System.out.println("User with id 1 has username: " + user.getBody().getUserName());
    }

    @Test
    public void testUpdateOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.updateUser(1,
            new UsersDTO(1, "adminz", "abc@yahoo.com", "admiN123$", AppUserRole.USER, new ArrayList<>(), false));
        System.out.println("Newly updated user has username: " + user.getBody().getUserName());
    }

    @Test
    public void testCreateOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.createUser(usersDTO);
        System.out.println("Newly created user has the name: " + user.getBody().getUserName());
    }

    @Test
    public void testDeactivateOfUser()
    {
        ResponseEntity<Object> user = userController.deactivateUser(1);
        System.out.println(user.getStatusCode());
    }

    @Test
    public void testGetTicketsForUser()
    {
        ResponseEntity<List<TicketDTO>> ticketsForUser = userController.getTicketsForUser(7);
        System.out.println(
            "The first ticket's name of user identified by id 7 is: " + ticketsForUser.getBody().get(0).getName());
    }

    @Test
    public void testChangeUserPassword()
    {
        String changedPassword = userController.changeUserPassword(6, "admiN1234$");
        System.out.println(changedPassword);
    }
}