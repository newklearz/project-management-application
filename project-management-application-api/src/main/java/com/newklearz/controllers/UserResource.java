package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.UsersDTO;

public interface UserResource
{
    String USER_COMMON_PREFIX = "/api/v1/users";

    @GetMapping(USER_COMMON_PREFIX)
    ResponseEntity<List<UsersDTO>> getUsers();

    @GetMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> getUser(@PathVariable("id") Integer id);

    @PostMapping(USER_COMMON_PREFIX)
    ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO);

    @PutMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UsersDTO usersDTO);

    @PutMapping(USER_COMMON_PREFIX + "/{id}/deactivate")
    ResponseEntity<Object> deactivateUser(@PathVariable("id") Integer id);

    @GetMapping(USER_COMMON_PREFIX + "/{id}/tickets")
    ResponseEntity<List<TicketDTO>> getTicketsForUser(@PathVariable("id") Integer id);

    @PostMapping(USER_COMMON_PREFIX + "/{id}/update_password")
    String changeUserPassword(@PathVariable("id") Integer id, @RequestBody String password);
}
