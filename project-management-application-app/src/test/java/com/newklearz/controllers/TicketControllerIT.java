package com.newklearz.controllers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.newklearz.ProjectManagementApplication;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;

@SpringBootTest(classes = ProjectManagementApplication.class)
public class TicketControllerIT
{

    @Autowired
    TicketController ticketController;
    TicketDTO ticketDTO;
    TicketDetailsDTO ticketDetailsDTO;

    @BeforeEach
    public void setUp()
    {
        ticketDetailsDTO = new TicketDetailsDTO(1, "Avem niste probleme urgente", "Dragonii albstri", "Ultimul");
        ticketDTO = new TicketDTO(1, "test", "bug", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", ticketDetailsDTO);
        ticketController.createTicket(ticketDTO);
        ticketDetailsDTO = new TicketDetailsDTO(2, "Avem niste probleme urgente", "Dragonii albstri", "Ultimul");
        ticketDTO = new TicketDTO(2, "test2", "bug", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", ticketDetailsDTO);
        ticketController.createTicket(ticketDTO);
    }

    @Test
    public void testRetrievalOfTickets()
    {
        ResponseEntity<List<TicketDTO>> tickets = ticketController.getTickets();
        System.out.println("The name of the first ticket: " + tickets.getBody().get(1).getName());
    }

    @Test
    public void testRetrievalOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(1);
        System.out.println("Ticket with id 1 has the name: " + ticket.getBody().getName());
    }

    @Test
    public void testCreateOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.createTicket(new TicketDTO(null, "test2", "bug2", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", new TicketDetailsDTO()));
        System.out.println("Newly created ticket has the type: " + ticket.getBody().getTicketType());
    }

    @Test
    public void testUpdateOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.updateTicket(1, new TicketDTO(1, "test3", "bug3", "01-12-2021", "02-12-2021", "inProgress", "resolved", "assignee", new TicketDetailsDTO()));
        System.out.println("Updated ticket has the status of " + ticket.getBody().getResolution());
    }


    @Test
    public void testRetrieveTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDetailsDTO> ticketDetails = ticketController.getTicketDetailsForTicket(1);
        System.out.println("Description of ticket with id 1: " + ticketDetails.getBody().getDescription());
    }

    @Test
    public void testUpdateTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDetailsDTO> ticketDetails = ticketController.updateTicketDetails(1, new TicketDetailsDTO(1, "Avem timp", "Dragonii albstri", "Ultimul"));
        System.out.println("Updated description of ticket with id 1: " + ticketDetails.getBody().getDescription());
    }

    @Test
    public void testCloneOfTicket() throws CloneNotSupportedException
    {
        ResponseEntity<TicketDTO> ticket = ticketController.cloneTicket(1);
        System.out.println("Id of cloned ticket is: " + ticket.getBody().getId());
    }

    @Test
    public void testDeleteOfTicket()
    {
        ResponseEntity<Object> ticket = ticketController.deleteTicket(2);
        System.out.println(ticket.getStatusCode());
    }
}