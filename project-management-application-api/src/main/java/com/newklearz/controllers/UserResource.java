package com.newklearz.controllers;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.UsersDTO;

@Api("UserResources")
public interface UserResource
{
    String USER_COMMON_PREFIX = "/api/v1/users";

    @ApiOperation("Retrieve all tickets 1")
    @ResponseBody
    @GetMapping(USER_COMMON_PREFIX)
    ResponseEntity<List<UsersDTO>> getUsers();

    @ApiOperation("Retrieve all tickets 2")
    @ResponseBody
    @GetMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> getUser(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 3")
    @ResponseBody
    @PostMapping(USER_COMMON_PREFIX)
    ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO);

    @ApiOperation("Retrieve all tickets 4")
    @ResponseBody
    @PutMapping(USER_COMMON_PREFIX + "/{id}")
    ResponseEntity<UsersDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UsersDTO usersDTO);

    @ApiOperation("Retrieve all tickets 5")
    @ResponseBody
    @PutMapping(USER_COMMON_PREFIX + "/{id}/deactivate")
    ResponseEntity<Object> deactivateUser(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 6")
    @ResponseBody
    @GetMapping(USER_COMMON_PREFIX + "/{id}/tickets")
    ResponseEntity<List<TicketDTO>> getTicketsForUser(@PathVariable("id") Integer id);

    @ApiOperation("Retrieve all tickets 7")
    @ResponseBody
    @PostMapping(USER_COMMON_PREFIX + "/{id}/update_password")
    String changeUserPassword(@PathVariable("id") Integer id, @RequestBody String password);
}
