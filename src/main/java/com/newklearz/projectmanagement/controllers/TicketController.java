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
import com.newklearz.projectmanagement.repository.ticketdetails.TicketDetails;
import com.newklearz.projectmanagement.service.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController
{
    private final TicketService ticketService;

    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets()
    {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket)
    {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Integer id, @RequestBody Ticket ticket)
    {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Integer id)
    {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @GetMapping("/{ticketId}/details")
    public ResponseEntity<TicketDetails> getTicketDetailsForTicket(@PathVariable("ticketId") Integer ticketId)
    {
        return ResponseEntity.ok(ticketService.findTicketDetailsForTicket(ticketId));
    }

    @PutMapping("/{ticketId}/details")
    public ResponseEntity<TicketDetails> addNewDetailsToTicket(@PathVariable("ticketId") Integer ticketId,
        @RequestBody TicketDetails ticketDetails)
    {
        return ResponseEntity.ok(ticketService.addNewDetailsToTicket(ticketId, ticketDetails));
    }

    @GetMapping("/{id}/clone")
    public ResponseEntity<Ticket> cloneTicket(@PathVariable("id") Integer id) throws CloneNotSupportedException
    {
        return ResponseEntity.ok(ticketService.cloneById(id));
    }
}
