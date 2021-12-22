package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newklearz.dto.TicketDTO;
import com.newklearz.dto.TicketDetailsDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Ticket")
public interface TicketResource
{
    String TICKET_COMMON_PREFIX = "/api/v1/tickets";

    @ApiOperation("Retrieves all tickets")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX)
    ResponseEntity<List<TicketDTO>> getTickets();

    @ApiOperation("Retrieves a ticket")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id);

    @ApiOperation("Creates a ticket")
    @ResponseBody
    @PostMapping(TICKET_COMMON_PREFIX)
    ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO);

    @ApiOperation("Updates a ticket")
    @ResponseBody
    @PutMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Integer id, @RequestBody TicketDTO ticketDTO);

    @ApiOperation("Deletes a ticket")
    @ResponseBody
    @DeleteMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<Object> deleteTicket(@PathVariable("id") Integer id);

    @ApiOperation("Retrieves ticketDetails for a ticket")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> getTicketDetailsForTicket(@PathVariable("id") Integer id);

    @ApiOperation("Updates ticketDetails for a ticket")
    @ResponseBody
    @PutMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> updateTicketDetails(@PathVariable("id") Integer id, @RequestBody TicketDetailsDTO ticketDetailsDTO);

    @ApiOperation("Retrieves all tickets created by a user")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/created")
    ResponseEntity<List<TicketDTO>> getAllTicketsCreatedByUser(@PathVariable("id") Integer id);

    @ApiOperation("Retrieves all tickets assigned to a user")
    @ResponseBody
    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/assigned")
    ResponseEntity<List<TicketDTO>> getAllTicketsAssignedToUser(@PathVariable("id") Integer id);

    @ApiOperation("Clones a ticket")
    @ResponseBody
    @PostMapping(TICKET_COMMON_PREFIX + "/{id}/clone")
    ResponseEntity<TicketDTO> cloneTicket(@PathVariable("id") Integer id) throws CloneNotSupportedException;
}