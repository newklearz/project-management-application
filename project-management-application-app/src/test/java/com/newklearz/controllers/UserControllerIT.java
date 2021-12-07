package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.UsersDTO;

public class UserControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRetrievalOfUsers()
    {
        ResponseEntity<List<UsersDTO>> users = userController.getUsers();
        assertNotNull(users, "The class must not be null");
        assertEquals(users.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrievalOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(7);
        assertEquals(7, user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeUpdate = userController.getUser(1);
        assertNotNull(foundUserBeforeUpdate);

        UsersDTO userBeforeUpdate = foundUserBeforeUpdate.getBody();
        userBeforeUpdate.setUserName("adminz");

        ResponseEntity<UsersDTO> requestUpdateUser = userController.updateUser(userBeforeUpdate.getId(), userBeforeUpdate);
        assertNotNull(requestUpdateUser);
        assertEquals(requestUpdateUser.getStatusCode(), HttpStatus.OK);

        ResponseEntity<UsersDTO> foundUserAfterUpdate = userController.getUser(userBeforeUpdate.getId());
        assertNotNull(foundUserAfterUpdate);

        UsersDTO userAfterUpdate = foundUserAfterUpdate.getBody();
        assertEquals(userBeforeUpdate.getUserName(), userAfterUpdate.getUserName());
    }

    @Test
    public void testCreateOfUser()
    {
        UsersDTO testUser = new UsersDTO();
        testUser.setId(8);
        testUser.setUserName("costinel");
        testUser.setEmail("costinel@gmail.com");
        testUser.setPassword("crocodiL123");
        testUser.setActive(true);
        testUser.setAppUserRole(AppUserRole.USER);
        testUser.setTicketList(Collections.emptyList());

        ResponseEntity<UsersDTO> user = userController.createUser(testUser);
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        UsersDTO userDTOFound = user.getBody();
        assertEquals(testUser.getId(), userDTOFound.getId());
        assertEquals(testUser.getUserName(), userDTOFound.getUserName());
        assertEquals(testUser.getEmail(), userDTOFound.getEmail());
        assertEquals(testUser.isActive(), userDTOFound.isActive());
        assertEquals(testUser.getAppUserRole(), userDTOFound.getAppUserRole());

    }

    @Test
    public void testDeactivateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeDeactivate = userController.getUser(1);
        assertNotNull(foundUserBeforeDeactivate);

        UsersDTO userBeforeDeactivate = foundUserBeforeDeactivate.getBody();
        userBeforeDeactivate.isActive();

        ResponseEntity<Object> requestDeactivateUser = userController.deactivateUser(userBeforeDeactivate.getId());
        assertNotNull(requestDeactivateUser);
        assertEquals(requestDeactivateUser.getStatusCode(), HttpStatus.LOCKED);

        ResponseEntity<UsersDTO> foundUserAfterDeactivate = userController.getUser(userBeforeDeactivate.getId());
        assertNotNull(foundUserAfterDeactivate);

        UsersDTO userAfterUpdate = foundUserAfterDeactivate.getBody();
        assertNotEquals(userBeforeDeactivate.isActive(), userAfterUpdate.isActive());
    }

    @Test
    public void testGetTicketsForUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(7);
        assertEquals(7, user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        List<TicketDTO> ticket = user.getBody().getTicketList();
        assertNotNull(ticket.get(1).getName(), "test2");
    }

    @Test
    public void testChangeUserPassword()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(6);
        assertEquals(6, user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        ResponseEntity<String> sendNewPassword = userController.changeUserPassword(user.getBody().getId(), "crocodiL123$");
        String expectedMessage = "Password changed successfully";
        assertNotNull(sendNewPassword);
        assertEquals(sendNewPassword.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedMessage, "Password changed successfully");
    }
}