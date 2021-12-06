package com.newklearz.controllers;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.AppUserRole;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.UsersDTO;

public class UserControllerIT extends SpringBootTestEnvironment
{
    /**
     * Verify if get request returns all registered users
     */
    @Test
    public void testRetrievalOfUsers()
    {
        ResponseEntity<List<UsersDTO>> users = userController.getUsers();
        Assertions.assertNotNull(users, "The class must not be null");
        Assertions.assertEquals(users.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request returns a user by id from database
     */
    @Test
    public void testRetrievalOfUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(7);
        Assertions.assertEquals(7, user.getBody().getId());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if put request updates a user in the database
     */
    @Test
    public void testUpdateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeUpdate = userController.getUser(1);
        Assertions.assertNotNull(foundUserBeforeUpdate);
        UsersDTO userBeforeUpdate = foundUserBeforeUpdate.getBody();
        userBeforeUpdate.setUserName("adminz");
        ResponseEntity<UsersDTO> requestUpdateUser = userController.updateUser(userBeforeUpdate.getId(), userBeforeUpdate);
        Assertions.assertNotNull(requestUpdateUser);
        Assertions.assertEquals(requestUpdateUser.getStatusCode(), HttpStatus.OK);
        ResponseEntity<UsersDTO> foundUserAfterUpdate = userController.getUser(userBeforeUpdate.getId());
        Assertions.assertNotNull(foundUserAfterUpdate);
        UsersDTO userAfterUpdate = foundUserAfterUpdate.getBody();
        Assertions.assertEquals(userBeforeUpdate.getUserName(), userAfterUpdate.getUserName());
    }

    /**
     * Verify if post request persists a user in the database
     */
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
        UsersDTO userDTOFound = user.getBody();
        Assertions.assertEquals(testUser.getId(), userDTOFound.getId());
        Assertions.assertEquals(testUser.getUserName(), userDTOFound.getUserName());
        Assertions.assertEquals(testUser.getEmail(), userDTOFound.getEmail());
        Assertions.assertEquals(testUser.isActive(), userDTOFound.isActive());
        Assertions.assertEquals(testUser.getAppUserRole(), userDTOFound.getAppUserRole());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if put request deactivates a user in database
     */
    @Test
    public void testDeactivateOfUser()
    {
        ResponseEntity<UsersDTO> foundUserBeforeDeactivate = userController.getUser(1);
        Assertions.assertNotNull(foundUserBeforeDeactivate);
        UsersDTO userBeforeDeactivate = foundUserBeforeDeactivate.getBody();
        userBeforeDeactivate.isActive();
        ResponseEntity<Object> requestDeactivateUser = userController.deactivateUser(userBeforeDeactivate.getId());
        Assertions.assertNotNull(requestDeactivateUser);
        Assertions.assertEquals(requestDeactivateUser.getStatusCode(), HttpStatus.LOCKED);
        ResponseEntity<UsersDTO> foundUserAfterDeactivate = userController.getUser(userBeforeDeactivate.getId());
        Assertions.assertNotNull(foundUserAfterDeactivate);
        UsersDTO userAfterUpdate = foundUserAfterDeactivate.getBody();
        Assertions.assertNotEquals(userBeforeDeactivate.isActive(), userAfterUpdate.isActive());
    }

    /**
     * Verify if get request returns all tickets for a user identified by an id
     */
    @Test
    public void testGetTicketsForUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(7);
        Assertions.assertEquals(7, user.getBody().getId());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getStatusCode(), HttpStatus.OK);

        List<TicketDTO> ticket = user.getBody().getTicketList();
        Assertions.assertNotNull(ticket.get(1).getName(), "test2");
    }

    /**
     * Verify if post request changes a user's password in database
     */
    @Test
    public void testChangeUserPassword()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(6);
        Assertions.assertEquals(6, user.getBody().getId());
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getStatusCode(), HttpStatus.OK);
        ResponseEntity<String> sendNewPassword = userController.changeUserPassword(user.getBody().getId(), "crocodiL123$");
        String expectedMessage = "Password changed successfully";
        Assertions.assertNotNull(sendNewPassword);
        Assertions.assertEquals(sendNewPassword.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(expectedMessage, "Password changed successfully");
    }
}