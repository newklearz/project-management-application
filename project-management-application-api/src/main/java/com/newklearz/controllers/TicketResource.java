package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;

public interface TicketResource
{
    String TICKET_COMMON_PREFIX = "/api/v1/tickets";

    @GetMapping(TICKET_COMMON_PREFIX)
    ResponseEntity<List<TicketDTO>> getTickets();

    @GetMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id);

    @PostMapping(TICKET_COMMON_PREFIX)
    ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO);

    @PutMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Integer id, @RequestBody TicketDTO ticketDTO);

    @DeleteMapping(TICKET_COMMON_PREFIX + "/{id}")
    ResponseEntity<Object> deleteTicket(@PathVariable("id") Integer id);

    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> getTicketDetailsForTicket(@PathVariable("id") Integer id);

    @PutMapping(TICKET_COMMON_PREFIX + "/{id}/details")
    ResponseEntity<TicketDetailsDTO> addNewDetailsToTicket(@PathVariable("id") Integer id,
        @RequestBody TicketDetailsDTO ticketDetailsDTO);

    @GetMapping(TICKET_COMMON_PREFIX + "/{id}/clone")
    ResponseEntity<TicketDTO> cloneTicket(@PathVariable("id") Integer id) throws CloneNotSupportedException;

}
