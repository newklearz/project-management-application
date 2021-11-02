package com.newklearz.projectmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newklearz.projectmanagement.repository.ticket.Ticket;
import com.newklearz.projectmanagement.repository.users.Users;
import com.newklearz.projectmanagement.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<Users>> getUsers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Users> createUser(@RequestBody Users user)
    {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Integer id,
        @RequestBody Users user)
    {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Integer id)
    {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.GONE).build();

    }

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsForUser(@PathVariable("userId") Integer userId)
    {

        return ResponseEntity.ok(userService.findAllTicketsByUser(userId));
    }

    @PostMapping("/{id}/update_password")
    public String changeUserPassword(@PathVariable("id") Integer id, @RequestBody String password)
    {

        return userService.updatePassword(id, password);

    }

}
