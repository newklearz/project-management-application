package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getRandomDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.dto.TicketDTO;
import com.newklearz.dto.TicketDetailsDTO;
import com.newklearz.dto.UsersDTO;

public class TicketControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRetrievalOfTickets()
    {
        ResponseEntity<List<TicketDTO>> tickets = ticketController.getTickets();
        assertNotNull(tickets, "The ticket array must not be null");
        assertEquals(tickets.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrievalOfTicket()
    {
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(ticketDTOS.get(0).getId());
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);

        TicketDTO retrievedTicket = ticket.getBody();
        assertNotNull(retrievedTicket);

        assertEquals(ticketDTOS.get(0).getId(), retrievedTicket.getId());
        assertEquals(ticketDTOS.get(0).getName(), retrievedTicket.getName());
        assertEquals(ticketDTOS.get(0).getTicketType(), retrievedTicket.getTicketType());
        assertEquals(ticketDTOS.get(0).getUserRole(), retrievedTicket.getUserRole());
        assertEquals(ticketDTOS.get(0).getResolution(), retrievedTicket.getResolution());
        assertEquals(ticketDTOS.get(0).getStatus(), retrievedTicket.getStatus());
        assertEquals(ticketDTOS.get(0).getDateCreated(), retrievedTicket.getDateCreated());
        assertEquals(ticketDTOS.get(0).getDateUpdated(), retrievedTicket.getDateUpdated());
    }

    @Test
    public void testCreateOfTicket()
    {
        TicketDTO testTicket = new TicketDTO(null, getAlphaNumericString(), getAlphaNumericString(), getRandomDate(),
            getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(),
            usersDTO, null, new TicketDetailsDTO());
        ResponseEntity<TicketDTO> ticket = ticketController.createTicket(testTicket);
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);

        TicketDTO ticketDTOFound = ticket.getBody();
        assertEquals(ticket.getBody().getId(), ticketDTOFound.getId());
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
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(ticketDTOS.get(0).getId());
        assertNotNull(foundTicketBeforeUpdate);

        TicketDTO ticketBeforeUpdate = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdate.setName("test2");

        ResponseEntity<TicketDTO> requestUpdateTicket =
            ticketController.updateTicket(ticketBeforeUpdate.getId(), ticketBeforeUpdate);
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
        ResponseEntity<TicketDTO> ticket = ticketController.getTicket(ticketDTOS.get(0).getId());
        assertEquals(ticketDTOS.get(0).getId(), ticket.getBody().getId());
        assertNotNull(ticket);
        assertEquals(ticket.getStatusCode(), HttpStatus.OK);

        TicketDetailsDTO ticketDetails = ticket.getBody().getTicketDetails();
        assertNotNull(ticketDetails);
        assertEquals(ticket.getBody().getId(), ticket.getBody().getTicketDetails().getId());
    }

    @Test
    public void testGetTicketsForCreatedUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(usersDTO.getId());
        assertEquals(usersDTO.getId(), user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        List<TicketDTO> ticket = ticketController.getAllTicketsCreatedByUser(user.getBody().getId()).getBody();
        assertNotNull(ticket.get(0).getName(), ticketDTOS.get(0).getName());
    }

    @Test
    public void testGetTicketsForAssignedUser()
    {
        ResponseEntity<UsersDTO> user = userController.getUser(ticketDTOS.get(0).getAssignedTo().getId());
        assertEquals(user.getBody().getId(), user.getBody().getId());
        assertNotNull(user);
        assertEquals(user.getStatusCode(), HttpStatus.OK);

        List<TicketDTO> ticket = ticketController.getAllTicketsAssignedToUser(user.getBody().getId()).getBody();
        assertNotNull(ticket.get(0).getName(), ticketDTOS.get(0).getName());
    }

    @Test
    public void testUpdateTicketDetailsOfTicket()
    {
        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(ticketDTOS.get(1).getId());
        assertNotNull(foundTicketBeforeUpdate);

        TicketDTO ticketBeforeUpdateTicketDetails = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdateTicketDetails.getTicketDetails().setDescription("Hello from Description");

        ResponseEntity<TicketDetailsDTO> requestUpdateTicketDetails = ticketController.updateTicketDetails(
            ticketBeforeUpdateTicketDetails.getId(), ticketBeforeUpdateTicketDetails.getTicketDetails());
        assertNotNull(requestUpdateTicketDetails);
        assertEquals(requestUpdateTicketDetails.getStatusCode(), HttpStatus.OK);

        ResponseEntity<TicketDetailsDTO> foundTicketDetailsAfterUpdate =
            ticketController.getTicketDetailsForTicket(ticketBeforeUpdateTicketDetails.getId());
        assertNotNull(foundTicketDetailsAfterUpdate);

        TicketDetailsDTO ticketDetailsAfterUpdate = foundTicketDetailsAfterUpdate.getBody();
        assertEquals(ticketBeforeUpdateTicketDetails.getTicketDetails().getDescription(),
            ticketDetailsAfterUpdate.getDescription());
    }

    @Test
    public void testCloneOfTicket() throws CloneNotSupportedException
    {
        ResponseEntity<TicketDTO> foundTicketBeforeClone = ticketController.getTicket(ticketDTOS.get(0).getId());
        assertNotNull(foundTicketBeforeClone);

        String ticketBeforeCloneName = foundTicketBeforeClone.getBody().getName();

        ResponseEntity<TicketDTO> clonedTicket = ticketController.cloneTicket(foundTicketBeforeClone.getBody().getId());
        assertNotNull(clonedTicket);
        assertEquals(clonedTicket.getStatusCode(), HttpStatus.OK);

        String ticketAfterCloneName = clonedTicket.getBody().getName();
        assertEquals(ticketAfterCloneName, "cloned " + ticketBeforeCloneName);
    }

    @Test
    public void testDeleteOfTicket()
    {
        ResponseEntity<Object> removeTicketFromBoard = ticketRankController.removeTicketFromBoard(ticketDTOS.get(1).getId(), boardDTO.getId());
        assertEquals(removeTicketFromBoard.getStatusCode(), HttpStatus.NO_CONTENT);

        ResponseEntity<List<TicketDTO>> ticketsForBoard = ticketRankController.getTicketsForBoard(boardDTO.getId());
        assertEquals(0, ticketsForBoard.getBody().size());

        ResponseEntity<Object> ticket = ticketController.deleteTicket(ticketDTOS.get(1).getId());
        assertEquals(ticket.getStatusCode(), HttpStatus.NO_CONTENT);
        assertThrows(EntityNotFoundException.class, () -> ticketController.getTicket(ticketDTOS.get(1).getId()));
    }
}