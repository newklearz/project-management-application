package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getMoreChars;
import static com.newklearz.controllers.Utils.getRandomDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void testRetrievalOfTicketNegative()
    {
        /**
         * Retrieve ticket with a negative id
         */
        ResponseEntity<TicketDTO> ticketNegativeID = ticketController.getTicket(Integer.MAX_VALUE + 3488799);
        assertEquals(HttpStatus.BAD_REQUEST, ticketNegativeID.getStatusCode());

        /**
         * Retrieve ticket with an in-existent id
         */
        ResponseEntity<TicketDTO> ticketInexistentId = ticketController.getTicket(213123123);
        assertEquals(HttpStatus.NOT_FOUND, ticketInexistentId.getStatusCode());

        /**
         * Retrieve ticket with id value of zero
         */
        ResponseEntity<TicketDTO> ticketZeroId = ticketController.getTicket(0);
        assertEquals(HttpStatus.BAD_REQUEST, ticketZeroId.getStatusCode());

        /**
         * Retrieve ticket with alphanumeric id value
         */
        ResponseEntity<TicketDTO> ticketAlphaNumericId = ticketController.getTicket(Integer.parseInt("asdaakjshd123sah"));
        assertEquals(HttpStatus.BAD_REQUEST, ticketAlphaNumericId);
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
    public void testCreateOfTicketNegative()
    {
        /**
         * Create ticket with null name
         */
        ResponseEntity<TicketDTO> testTicketNullName = ticketController.createTicket(new TicketDTO(null, null, getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testTicketNullName);

        /**
         * Create ticket with empty string name
         */
        ResponseEntity<TicketDTO> testEmptyStringName = ticketController.createTicket(new TicketDTO(null, "", getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testEmptyStringName.getStatusCode());

        /**
         * Create ticket with blank space name
         */
        ResponseEntity<TicketDTO> testBlankSpaceName = ticketController.createTicket(new TicketDTO(null, " ", getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testBlankSpaceName.getStatusCode());
        /**
         * Create ticket with same name as an existing one
         */
        ResponseEntity<TicketDTO> testSameName = ticketController.createTicket(new TicketDTO(null, ticketDTOS.get(0).getName(), getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.CONFLICT, testSameName.getStatusCode());

        /**
         * Create ticket with more than 100 characters in name
         */
        ResponseEntity<TicketDTO> testMoreCharsName = ticketController.createTicket(new TicketDTO(null, getMoreChars(),getAlphaNumericString(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testMoreCharsName.getStatusCode());

        /**
         * Create ticket with null date crated
         */
        ResponseEntity<TicketDTO> testCreateDate = ticketController.createTicket(new TicketDTO(null, getAlphaNumericString(),getAlphaNumericString(),null, getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")), testCreateDate.getBody().getDateCreated());

        /**
         * Create ticket with null ticket type
         */
        ResponseEntity<TicketDTO> testTicketNullTicketType= ticketController.createTicket(new TicketDTO(null, getAlphaNumericString(), null, getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testTicketNullTicketType);

        /**
         * Create ticket with empty string ticket type
         */
        ResponseEntity<TicketDTO> testEmptyStringTicketType = ticketController.createTicket(new TicketDTO(null, getAlphaNumericString(), "", getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testEmptyStringTicketType.getStatusCode());

        /**
         * Create ticket with blank space ticket type
         */
        ResponseEntity<TicketDTO> testBlankSpaceTicketType = ticketController.createTicket(new TicketDTO(null, getAlphaNumericString(), " ", getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testBlankSpaceTicketType.getStatusCode());

        /**
         * Create ticket with more than 100 characters in ticket type
         */
        ResponseEntity<TicketDTO> testMoreCharsTicketType = ticketController.createTicket(new TicketDTO(null, getAlphaNumericString(),getMoreChars(), getRandomDate(), getRandomDate(), getAlphaNumericString(), getAlphaNumericString(), getAlphaNumericString(), usersDTO, null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testMoreCharsTicketType.getStatusCode());
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
    public void testUpdateOfTicketNegative()
    {

        ResponseEntity<TicketDTO> foundTicketBeforeUpdate = ticketController.getTicket(ticketDTOS.get(0).getId());
        assertNotNull(foundTicketBeforeUpdate);
        assertEquals(HttpStatus.OK, foundTicketBeforeUpdate.getStatusCode());

        TicketDTO ticketBeforeUpdate = foundTicketBeforeUpdate.getBody();
        ticketBeforeUpdate.setName(ticketDTOS.get(1).getName());

        /**
         * Update ticket with an existing name
         */
        ResponseEntity<TicketDTO> requestUpdateTicket = ticketController.updateTicket(ticketBeforeUpdate.getId(), ticketBeforeUpdate);
        assertEquals(HttpStatus.CONFLICT, requestUpdateTicket.getStatusCode());
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
    public void testGetTicketsForCreatedUserNegative()
    {
        /**
         * Retrieve tickets created by user with an in-existent user id
         */
        ResponseEntity<List<TicketDTO>> ticketsCreatedByInexistentUser = ticketController.getAllTicketsCreatedByUser(213123123);
        assertEquals(HttpStatus.NOT_FOUND, ticketsCreatedByInexistentUser.getStatusCode());

        /**
         * Retrieve tickets created by user with a negative id
         */
        ResponseEntity<List<TicketDTO>>  ticketsCreatedByNegativeIdUser = ticketController.getAllTicketsCreatedByUser(Integer.MAX_VALUE + 3488799);
        assertEquals(HttpStatus.BAD_REQUEST, ticketsCreatedByNegativeIdUser.getStatusCode());

        /**
         * Retrieve tickets created by user with id value of zero
         */
        ResponseEntity<List<TicketDTO>>  ticketZeroId = ticketController.getAllTicketsCreatedByUser(0);
        assertEquals(HttpStatus.BAD_REQUEST, ticketZeroId.getStatusCode());

        /**
         * Retrieve tickets created by user with alphanumeric id value
         */
        ResponseEntity<List<TicketDTO>> ticketAlphaNumericId = ticketController.getAllTicketsCreatedByUser(Integer.parseInt("asdaakjshd123sah"));
        assertEquals(HttpStatus.BAD_REQUEST, ticketAlphaNumericId);
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
    public void testGetTicketsForAssignedUserNegative()
    {
        /**
         * Retrieve tickets assigned to user with an in-existent user id
         */
        ResponseEntity<List<TicketDTO>> ticketsAssignedToInexistentUser = ticketController.getAllTicketsAssignedToUser(213123123);
        assertEquals(HttpStatus.NOT_FOUND, ticketsAssignedToInexistentUser.getStatusCode());

        /**
         * Retrieve tickets assigned to user with a negative id
         */
        ResponseEntity<List<TicketDTO>> ticketsAssignedToNegativeIdUser = ticketController.getAllTicketsAssignedToUser(Integer.MAX_VALUE + 3488799);
        assertEquals(HttpStatus.BAD_REQUEST, ticketsAssignedToNegativeIdUser.getStatusCode());

        /**
         * Retrieve tickets assigned to user with id value of zero
         */
        ResponseEntity<List<TicketDTO>> ticketZeroId = ticketController.getAllTicketsAssignedToUser(0);
        assertEquals(HttpStatus.BAD_REQUEST, ticketZeroId.getStatusCode());

        /**
         * Retrieve tickets assigned to user with alphanumeric id value
         */
        ResponseEntity<List<TicketDTO>> ticketAlphaNumericId = ticketController.getAllTicketsAssignedToUser(Integer.parseInt("asdaakjshd123sah"));
        assertEquals(HttpStatus.BAD_REQUEST, ticketAlphaNumericId);
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