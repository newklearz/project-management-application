package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.TicketRankDTO;

public class TicketRankControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testAddTicketToBoard()
    {
        TicketRankDTO testRank = new TicketRankDTO();
        ResponseEntity<TicketRankDTO> ticketRank = ticketRankController.addTicketToBoard(testRank);
        assertNotNull(ticketRank);
        assertEquals(ticketRank.getStatusCode(), HttpStatus.OK);

        TicketRankDTO ticketRankDTOFound = ticketRank.getBody();
        assertEquals(testRank.getTicketRank(), ticketRankDTOFound.getTicketRank());
    }
}
