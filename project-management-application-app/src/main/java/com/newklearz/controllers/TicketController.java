package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;
import com.newklearz.service.TicketService;

@RestController
public class TicketController implements TicketResource
{
    private final TicketService ticketService;

    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getTickets()
    {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @Override
    public ResponseEntity<TicketDTO> getTicket(Integer id)
    {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @Override
    public ResponseEntity<TicketDTO> createTicket(TicketDTO ticketDTO)
    {
        return ResponseEntity.ok(ticketService.createTicket(ticketDTO));
    }

    @Override
    public ResponseEntity<TicketDTO> updateTicket(Integer id, TicketDTO ticketDTO)
    {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticketDTO));
    }

    @Override
    public ResponseEntity<Object> deleteTicket(Integer id)
    {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<TicketDetailsDTO> getTicketDetailsForTicket(Integer id)
    {
        return ResponseEntity.ok(ticketService.findTicketDetailsForTicket(id));
    }

    @Override
    public ResponseEntity<TicketDetailsDTO> updateTicketDetails(Integer id, TicketDetailsDTO ticketDetailsDTO)
    {
        return ResponseEntity.ok(ticketService.updateTicketDetails(id, ticketDetailsDTO));
    }

    @Override
    public ResponseEntity<TicketDTO> cloneTicket(Integer id) throws CloneNotSupportedException
    {
        return ResponseEntity.ok(ticketService.cloneById(id));
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getAllTicketsCreatedByUser(Integer id)
    {
        return ResponseEntity.ok(ticketService.findAllTicketsCreatedByUser(id));
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getAllTicketsAssignedToUser(Integer id)
    {
        return ResponseEntity.ok(ticketService.findAllTicketsAssignedToUser(id));
    }
}