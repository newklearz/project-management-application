package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.BoardDTO;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketRankDTO;

import javax.persistence.EntityNotFoundException;

public class TicketRankControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testAddTicketToBoard()
    {
        TicketRankDTO testTicketRank = new TicketRankDTO();
        testTicketRank.setAssignedTicket(ticketDTOS.get(0));
        testTicketRank.setAssignedBoard(boardDTO);
        testTicketRank.setTicketRank(125);

        ResponseEntity<TicketRankDTO> ticketRank = ticketRankController.addTicketToBoard(testTicketRank);
        assertNotNull(ticketRank);
        assertEquals(ticketRank.getStatusCode(), HttpStatus.OK);

        TicketRankDTO ticketRankDTOFound = ticketRank.getBody();
        assertEquals(testTicketRank.getTicketRank(), ticketRankDTOFound.getTicketRank());
        assertEquals(testTicketRank.getAssignedTicket().getId(), ticketRankDTOFound.getAssignedTicket().getId());
        assertEquals(testTicketRank.getAssignedBoard().getId(), ticketRankDTOFound.getAssignedBoard().getId());
    }

    @Test
    public void testGetTicketsForBoard()
    {
        ResponseEntity<BoardDTO> createdBoard = boardController.getBoard(boardDTO.getId());
        assertEquals(createdBoard.getStatusCode(), HttpStatus.OK);
        assertEquals(boardDTO.getId(), createdBoard.getBody().getId());
        assertNotNull(createdBoard);

        ResponseEntity<List<TicketDTO>> ticketsForBoard = ticketRankController.getTicketsForBoard(boardDTO.getId());
        assertEquals(ticketsForBoard.getStatusCode(), HttpStatus.OK);
        assertEquals(1, ticketsForBoard.getBody().size());
        assertNotNull(ticketsForBoard.getBody());
    }

    @Test
    public void testGetBoardsForTicket()
    {
        ResponseEntity<TicketDTO> createdTicket = ticketController.getTicket(ticketDTOS.get(1).getId());
        assertEquals(createdTicket.getStatusCode(), HttpStatus.OK);
        assertEquals(ticketDTOS.get(1).getId(), createdTicket.getBody().getId());
        assertNotNull(createdTicket);

        ResponseEntity<List<BoardDTO>> boardForTickets = ticketRankController.getBoardsForTicket(createdTicket.getBody().getId());
        assertEquals(boardForTickets.getStatusCode(), HttpStatus.OK);
        assertEquals(1, boardForTickets.getBody().size());
        assertNotNull(boardForTickets.getBody());
    }

    @Test
    public void testRemoveTicketFromBoard()
    {
        ResponseEntity<Object> removeTicketFromBoard = ticketRankController.removeTicketFromBoard(ticketDTOS.get(1).getId(), boardDTO.getId());
        assertEquals(removeTicketFromBoard.getStatusCode(), HttpStatus.NO_CONTENT);

        ResponseEntity<List<TicketDTO>> ticketsForBoard = ticketRankController.getTicketsForBoard(boardDTO.getId());
        assertEquals(0, ticketsForBoard.getBody().size());
    }
}