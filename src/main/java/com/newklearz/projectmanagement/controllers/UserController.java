package com.newklearz.projectmanagement.controllers;


import com.newklearz.projectmanagement.repository.ticket.Ticket;
import com.newklearz.projectmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsForUser(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(userService.findAllTicketsByUser(userId));
    }


}
