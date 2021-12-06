package com.newklearz.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;

public class TicketControllerIT extends SpringBootTestEnvironment
{
    /**
     * Verify if get request returns all tickets
     */
    @Test
    public void testRetrievalOfTickets()
    {
        ResponseEntity<List<TicketDTO>> tickets = ticketController.getTickets();
        Assertions.assertNotNull(tickets, "The class must not be null");
        Assertions.assertEquals(tickets.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if get request returns a ticket by id from database
     */
    @Test
    public void testRetrievalOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(1);
        Assertions.assertEquals(1, ticket.getBody().getId());
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if post request persists a ticket in the database
     */
    @Test
    public void testCreateOfTicket()
    {
        TicketDTO testTicket = new TicketDTO(3, "test2", "bug2", "01-12-2021", "02-12-2021", "inProgress", "unsolved", "assignee", new TicketDetailsDTO());
        ResponseEntity<TicketDTO> ticket = ticketController.createTicket(testTicket);
        TicketDTO ticketDTOFound = ticket.getBody();
        Assertions.assertEquals(testTicket.getId(), ticketDTOFound.getId());
        Assertions.assertEquals(testTicket.getName(), ticketDTOFound.getName());
        Assertions.assertEquals(testTicket.getTicketType(), ticketDTOFound.getTicketType());
        Assertions.assertEquals(testTicket.getDateCreated(), ticketDTOFound.getDateCreated());
        Assertions.assertEquals(testTicket.getDateUpdated(), ticketDTOFound.getDateUpdated());
        Assertions.assertEquals(testTicket.getStatus(), ticketDTOFound.getStatus());
        Assertions.assertEquals(testTicket.getResolution(), ticketDTOFound.getResolution());
        Assertions.assertEquals(testTicket.getUserRole(), ticketDTOFound.getUserRole());
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Verify if put request updates a ticket in the database
     */
    @Test
    public void testUpdateOfTicket()
    {
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(1);
        Assertions.assertNotNull(foundTicketBeforeUpdate);
        TicketDTO ticketBeforeUpdate = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdate.setName("test2");
        ResponseEntity<TicketDTO> requestUpdateTicket = ticketController.updateTicket(ticketBeforeUpdate.getId(),ticketBeforeUpdate);
        Assertions.assertNotNull(requestUpdateTicket);
        Assertions.assertEquals(requestUpdateTicket.getStatusCode(), HttpStatus.OK);
        ResponseEntity<TicketDTO> foundTicketAfterUpdate = ticketController.getTicket(ticketBeforeUpdate.getId());
        Assertions.assertNotNull(foundTicketAfterUpdate);
        TicketDTO ticketAfterUpdate = foundTicketAfterUpdate.getBody();
        Assertions.assertEquals(ticketBeforeUpdate.getName(), ticketAfterUpdate.getName());

    }

    /**
     * Verify if get request returns all ticketsDetails for a ticket identified by an id
     */
    @Test
    public void testRetrieveTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(1);
        Assertions.assertEquals(1, ticket.getBody().getId());
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(ticket.getStatusCode(), HttpStatus.OK);
        TicketDetailsDTO ticketDetails = ticket.getBody().getTicketDetails();
        Assertions.assertNotNull(ticketDetails);
        Assertions.assertEquals(ticket.getBody().getId(), ticket.getBody().getTicketDetails().getId());
    }

    /**
     * Verify if put request updates ticketsDetails for a ticket identified by an id
     */
    @Test
    public void testUpdateTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(1);
        Assertions.assertNotNull(foundTicketBeforeUpdate);
        TicketDTO ticketBeforeUpdateTicketDetails = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdateTicketDetails.getTicketDetails().setDescription("Hello from Description");
        ResponseEntity<TicketDetailsDTO> requestUpdateTicketDetails = ticketController.updateTicketDetails(ticketBeforeUpdateTicketDetails.getId(),ticketBeforeUpdateTicketDetails.getTicketDetails());
        Assertions.assertNotNull(requestUpdateTicketDetails);
        Assertions.assertEquals(requestUpdateTicketDetails.getStatusCode(), HttpStatus.OK);
        ResponseEntity<TicketDetailsDTO> foundTicketDetailsAfterUpdate = ticketController.getTicketDetailsForTicket(ticketBeforeUpdateTicketDetails.getId());
        Assertions.assertNotNull(foundTicketDetailsAfterUpdate);
        TicketDetailsDTO ticketDetailsAfterUpdate = foundTicketDetailsAfterUpdate.getBody();
        Assertions.assertEquals(ticketBeforeUpdateTicketDetails.getTicketDetails().getDescription(), ticketDetailsAfterUpdate.getDescription());
    }

    /**
     * Verify if post request clones a ticket based on an existing ticket's id
     */
    @Test
    public void testCloneOfTicket() throws CloneNotSupportedException
    {
        ResponseEntity<TicketDTO> foundTicketBeforeClone= ticketController.getTicket(1);
        Assertions.assertNotNull(foundTicketBeforeClone);
        String ticketBeforeCloneName = foundTicketBeforeClone.getBody().getName();
        ResponseEntity<TicketDTO> clonedTicket = ticketController.cloneTicket(foundTicketBeforeClone.getBody().getId());
        Assertions.assertNotNull(clonedTicket);
        Assertions.assertEquals(clonedTicket.getStatusCode(), HttpStatus.OK);
        String ticketAfterCloneName = clonedTicket.getBody().getName();
        Assertions.assertEquals(ticketAfterCloneName, "cloned " +ticketBeforeCloneName);
    }

    /**
     * Verify if delete request deletes a ticket in database
     */
    @Test
    public void testDeleteOfTicket()
    {
        ResponseEntity<Object> ticket = ticketController.deleteTicket(2);
       Assertions.assertEquals(ticket.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}