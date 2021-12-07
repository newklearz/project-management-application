package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;

public class TicketControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRetrievalOfTickets()
    {
        ResponseEntity<List<TicketDTO>> tickets = ticketController.getTickets();
        assertNotNull(tickets, "The class must not be null");
        assertEquals(tickets.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrievalOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(1);
        assertEquals(1, ticket.getBody().getId());
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreateOfTicket()
    {
        TicketDTO testTicket = new TicketDTO(3, "test2", "bug2", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", new TicketDetailsDTO());
        ResponseEntity<TicketDTO> ticket = ticketController.createTicket(testTicket);
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);
        
        TicketDTO ticketDTOFound = ticket.getBody();
        assertEquals(testTicket.getId(), ticketDTOFound.getId());
        assertEquals(testTicket.getName(), ticketDTOFound.getName());
        assertEquals(testTicket.getTicketType(), ticketDTOFound.getTicketType());
        assertEquals(testTicket.getDateCreated(), ticketDTOFound.getDateCreated());
        assertEquals(testTicket.getDateUpdated(), ticketDTOFound.getDateUpdated());
        assertEquals(testTicket.getStatus(), ticketDTOFound.getStatus());
        assertEquals(testTicket.getResolution(), ticketDTOFound.getResolution());
        assertEquals(testTicket.getUserRole(), ticketDTOFound.getUserRole());
    }

    @Test
    public void testUpdateOfTicket()
    {
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(1);
        assertNotNull(foundTicketBeforeUpdate);

        TicketDTO ticketBeforeUpdate = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdate.setName("test2");

        ResponseEntity<TicketDTO> requestUpdateTicket = ticketController.updateTicket(ticketBeforeUpdate.getId(),ticketBeforeUpdate);
        assertNotNull(requestUpdateTicket);
        assertEquals(requestUpdateTicket.getStatusCode(), HttpStatus.OK);
        
        ResponseEntity<TicketDTO> foundTicketAfterUpdate = ticketController.getTicket(ticketBeforeUpdate.getId());
        assertNotNull(foundTicketAfterUpdate);

        TicketDTO ticketAfterUpdate = foundTicketAfterUpdate.getBody();
        assertEquals(ticketBeforeUpdate.getName(), ticketAfterUpdate.getName());
    }

    @Test
    public void testRetrieveTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(1);
        assertEquals(1, ticket.getBody().getId());
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);

        TicketDetailsDTO ticketDetails = ticket.getBody().getTicketDetails();
        assertNotNull(ticketDetails);
        assertEquals(ticket.getBody().getId(), ticket.getBody().getTicketDetails().getId());
    }

    @Test
    public void testUpdateTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(1);
        assertNotNull(foundTicketBeforeUpdate);

        TicketDTO ticketBeforeUpdateTicketDetails = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdateTicketDetails.getTicketDetails().setDescription("Hello from Description");

        ResponseEntity<TicketDetailsDTO> requestUpdateTicketDetails = ticketController.updateTicketDetails(ticketBeforeUpdateTicketDetails.getId(),ticketBeforeUpdateTicketDetails.getTicketDetails());
        assertNotNull(requestUpdateTicketDetails);
        assertEquals(requestUpdateTicketDetails.getStatusCode(), HttpStatus.OK);

        ResponseEntity<TicketDetailsDTO> foundTicketDetailsAfterUpdate = ticketController.getTicketDetailsForTicket(ticketBeforeUpdateTicketDetails.getId());
        assertNotNull(foundTicketDetailsAfterUpdate);

        TicketDetailsDTO ticketDetailsAfterUpdate = foundTicketDetailsAfterUpdate.getBody();
        assertEquals(ticketBeforeUpdateTicketDetails.getTicketDetails().getDescription(), ticketDetailsAfterUpdate.getDescription());
    }

    @Test
    public void testCloneOfTicket() throws CloneNotSupportedException
    {
        ResponseEntity<TicketDTO> foundTicketBeforeClone= ticketController.getTicket(1);
        assertNotNull(foundTicketBeforeClone);

        String ticketBeforeCloneName = foundTicketBeforeClone.getBody().getName();

        ResponseEntity<TicketDTO> clonedTicket = ticketController.cloneTicket(foundTicketBeforeClone.getBody().getId());
        assertNotNull(clonedTicket);
        assertEquals(clonedTicket.getStatusCode(), HttpStatus.OK);

        String ticketAfterCloneName = clonedTicket.getBody().getName();
        assertEquals(ticketAfterCloneName, "cloned " +ticketBeforeCloneName);
    }

    @Test
    public void testDeleteOfTicket()
    {
        ResponseEntity<Object> ticket = ticketController.deleteTicket(2);
        assertEquals(ticket.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}