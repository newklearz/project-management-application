package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newklearz.DTO.UsersDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("User")
public interface UserResource
{
    String USER_COMMON_PREFIX = "/api/v1/users";

    @ApiOperation("Retrieve all users")
    @ResponseBody
    @GetMapping(USER_COMMON_PREFIX)
    ResponseEntity<List<UsersDTO>> getUsers();

    @ApiOperation("Retrieves a user")
    @ResponseBody
    @GetMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> getUser(@PathVariable("id") Integer id);

    @ApiOperation("Creates a user")
    @ResponseBody
    @PostMapping(USER_COMMON_PREFIX)
    ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO);

    @ApiOperation("Updates a user")
    @ResponseBody
    @PutMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UsersDTO usersDTO);

    @ApiOperation("Deactivates a user")
    @ResponseBody
    @PutMapping(USER_COMMON_PREFIX + "/{id}/deactivate")
    ResponseEntity<Object> deactivateUser(@PathVariable("id") Integer id);

    @ApiOperation("Changes user's password")
    @ResponseBody
    @PostMapping(USER_COMMON_PREFIX + "/{id}/update_password")
    ResponseEntity<String> changeUserPassword(@PathVariable("id") Integer id, @RequestBody String password);
}