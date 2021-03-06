package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.newklearz.dto.UsersDTO;
import com.newklearz.service.UserService;

@RestController
public class UserController implements UserResource
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UsersDTO>> getUsers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<UsersDTO> getUser(Integer id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UsersDTO> createUser(UsersDTO usersDTO)
    {
        return ResponseEntity.ok(userService.createUser(usersDTO));
    }

    @Override
    public ResponseEntity<UsersDTO> updateUser(Integer id, UsersDTO usersDTO)
    {
        return ResponseEntity.ok(userService.updateUser(id, usersDTO));
    }

    @Override
    public ResponseEntity<Object> deactivateUser(Integer id)
    {
        userService.deactivateUser(id);
        return ResponseEntity.status(HttpStatus.LOCKED).build();
    }

    @Override
    public ResponseEntity<String> changeUserPassword(Integer id, String password)
    {
        return ResponseEntity.ok(userService.updatePassword(id, password));
    }
}