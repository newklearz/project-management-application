package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getIntegerZero;
import static com.newklearz.controllers.Utils.getOutOfRangeValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.dto.BoardDTO;
import com.newklearz.dto.TicketDTO;
import com.newklearz.dto.TicketRankDTO;

public class TicketRankControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testAddTicketToBoard()
    {
        TicketRankDTO testTicketRank = new TicketRankDTO();
        testTicketRank.setAssignedTicket(ticketDTOS.get(0));
        testTicketRank.setAssignedBoard(boardDTO);

        ResponseEntity<TicketRankDTO> ticketRank = ticketRankController.addTicketToBoard(boardDTO.getId(),testTicketRank);
        assertNotNull(ticketRank);
        assertEquals(ticketRank.getStatusCode(), HttpStatus.OK);

        TicketRankDTO ticketRankDTOFound = ticketRank.getBody();
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
    public void testGetTicketsForBoardNegative()
    {
        /**
         * Retrieve tickets for board with an in-existent id
         */
        ResponseEntity<List<TicketDTO>> ticketsForInexistentBoardId = ticketRankController.getTicketsForBoard(Integer.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, ticketsForInexistentBoardId.getStatusCode());

        /**
         * Retrieve tickets for board with a negative id
         */
        ResponseEntity<List<TicketDTO>>  ticketsForNegativeBoardId = ticketRankController.getTicketsForBoard(Integer.MIN_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, ticketsForNegativeBoardId.getStatusCode());

        /**
         * Retrieve tickets for board with id value of zero
         */
        ResponseEntity<List<TicketDTO>>  ticketsForZeroBoardId = ticketRankController.getTicketsForBoard(getIntegerZero());
        assertEquals(HttpStatus.BAD_REQUEST, ticketsForZeroBoardId.getStatusCode());

        /**
         * Retrieve tickets for board with out of range Integer id value
         */
        ResponseEntity<List<TicketDTO>> ticketsForOutOfRangeIntegerBoardId = ticketRankController.getTicketsForBoard(getOutOfRangeValue());
        assertEquals(HttpStatus.BAD_REQUEST, ticketsForOutOfRangeIntegerBoardId);
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
    public void testGetBoardsForTicketNegative()
    {
        /**
         * Retrieve boards for ticket with an in-existent id
         */
        ResponseEntity<List<BoardDTO>> boardsForInexistentTicketId = ticketRankController.getBoardsForTicket(Integer.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, boardsForInexistentTicketId.getStatusCode());

        /**
         * Retrieve boards for ticket with a negative id
         */
        ResponseEntity<List<BoardDTO>>  boardsForNegativeTicketId = ticketRankController.getBoardsForTicket(Integer.MIN_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, boardsForNegativeTicketId.getStatusCode());

        /**
         * Retrieve boards for ticket with id value of zero
         */
        ResponseEntity<List<BoardDTO>>  boardsForZeroTicketId = ticketRankController.getBoardsForTicket(getIntegerZero());
        assertEquals(HttpStatus.BAD_REQUEST, boardsForZeroTicketId.getStatusCode());

        /**
         * Retrieve boards for ticket with out of range Integer id value
         */
        ResponseEntity<List<BoardDTO>> boardsForOutOfRangeIntegerTicketId = ticketRankController.getBoardsForTicket(getOutOfRangeValue());
        assertEquals(HttpStatus.BAD_REQUEST, boardsForOutOfRangeIntegerTicketId);
    }

    @Test
    public void testRemoveTicketFromBoard()
    {
        ResponseEntity<Object> removeTicketFromBoard = ticketRankController.removeTicketFromBoard(ticketDTOS.get(1).getId(), boardDTO.getId());
        assertEquals(removeTicketFromBoard.getStatusCode(), HttpStatus.NO_CONTENT);

        ResponseEntity<List<TicketDTO>> ticketsForBoard = ticketRankController.getTicketsForBoard(boardDTO.getId());
        assertEquals(0, ticketsForBoard.getBody().size());
    }

    @Test
    public void testUpdateTicketPriority()
    {
        TicketRankDTO testTicketRank = new TicketRankDTO();
        testTicketRank.setAssignedTicket(ticketDTOS.get(0));
        testTicketRank.setAssignedBoard(boardDTO);

        ResponseEntity<TicketRankDTO> ticketRank = ticketRankController.addTicketToBoard(boardDTO.getId(),testTicketRank);
        ticketRankDTOS.add(ticketRank.getBody());
        assertNotNull(ticketRank);
        assertEquals(ticketRank.getStatusCode(), HttpStatus.OK);

        TicketRankDTO testTicketRank3 = new TicketRankDTO();
        testTicketRank3.setAssignedTicket(ticketDTOS.get(2));
        testTicketRank3.setAssignedBoard(boardDTO);

        ResponseEntity<TicketRankDTO> ticketRank3 = ticketRankController.addTicketToBoard(boardDTO.getId(),testTicketRank3);
        ticketRankDTOS.add(ticketRank3.getBody());
        assertNotNull(ticketRank3);
        assertEquals(ticketRank3.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Object> updateTicketPriority = ticketRankController.updateTicketPriority(boardDTO.getId(),1,2);
        assertEquals(updateTicketPriority.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<TicketDTO>> ticketsForBoard = ticketRankController.getTicketsForBoard(boardDTO.getId());
        assertEquals(ticketsForBoard.getStatusCode(), HttpStatus.OK);
        assertNotNull(ticketsForBoard.getBody());
        assertEquals(ticketDTOS.size(), ticketsForBoard.getBody().size());
        assertEquals(ticketsForBoard.getBody().get(1).getId(),ticketRankDTOS.get(2).getAssignedTicket().getId());
    }

    @Test
    public void testUpdateTicketPriorityNegative()
    {
        TicketRankDTO testTicketRank = new TicketRankDTO();
        testTicketRank.setAssignedTicket(ticketDTOS.get(0));
        testTicketRank.setAssignedBoard(boardDTO);

        ResponseEntity<TicketRankDTO> ticketRank = ticketRankController.addTicketToBoard(boardDTO.getId(),testTicketRank);
        ticketRankDTOS.add(ticketRank.getBody());
        assertNotNull(ticketRank);
        assertEquals(ticketRank.getStatusCode(), HttpStatus.OK);

        TicketRankDTO testTicketRank3 = new TicketRankDTO();
        testTicketRank3.setAssignedTicket(ticketDTOS.get(2));
        testTicketRank3.setAssignedBoard(boardDTO);

        ResponseEntity<TicketRankDTO> ticketRank3 = ticketRankController.addTicketToBoard(boardDTO.getId(),testTicketRank3);
        ticketRankDTOS.add(ticketRank3.getBody());
        assertNotNull(ticketRank3);
        assertEquals(ticketRank3.getStatusCode(), HttpStatus.OK);

        /**
         * Update rank priority with outOfBounds positions
         */
        ResponseEntity<Object> outOfBoundsPositions = ticketRankController.updateTicketPriority(boardDTO.getId(),Integer.MAX_VALUE-2,Integer.MAX_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, outOfBoundsPositions.getStatusCode());

        /**
         * Update rank priority with negative positions
         */
        ResponseEntity<Object> negativeArrayPositions = ticketRankController.updateTicketPriority(boardDTO.getId(),Integer.MIN_VALUE,Integer.MIN_VALUE+2);
        assertEquals(HttpStatus.BAD_REQUEST, negativeArrayPositions.getStatusCode());

        /**
         * Update rank priority with out of range Integer positions
         */
        ResponseEntity<Object> outOfRangeIntegerPositions = ticketRankController.updateTicketPriority(boardDTO.getId(),getOutOfRangeValue(),getOutOfRangeValue());
        assertEquals(HttpStatus.BAD_REQUEST, outOfRangeIntegerPositions.getStatusCode());

        /**
         * Update rank priority with null positions
         */
        ResponseEntity<Object> nullPositions = ticketRankController.updateTicketPriority(boardDTO.getId(),null,null);
        assertEquals(HttpStatus.BAD_REQUEST, nullPositions.getStatusCode());

        /**
         * Update rank priority for board with an in-existent id
         */
        ResponseEntity<Object> inexistentBoardId = ticketRankController.updateTicketPriority(Integer.MAX_VALUE,1,2);
        assertEquals(HttpStatus.NOT_FOUND, inexistentBoardId.getStatusCode());

        /**
         * Update rank priority for board with negative id
         */
        ResponseEntity<Object> negativeBoardId = ticketRankController.updateTicketPriority(Integer.MIN_VALUE,1,2);
        assertEquals(HttpStatus.BAD_REQUEST, negativeBoardId.getStatusCode());

        /**
         * Update rank priority for board with id zero
         */
        ResponseEntity<Object> zeroBoardId = ticketRankController.updateTicketPriority(getIntegerZero(),1,2);
        assertEquals(HttpStatus.BAD_REQUEST, zeroBoardId.getStatusCode());

        /**
         * Update rank priority for board with out of range Integer id
         */
        ResponseEntity<Object> outOfRangeIntegerBoardId = ticketRankController.updateTicketPriority(getOutOfRangeValue(),1,2);
        assertEquals(HttpStatus.BAD_REQUEST, outOfRangeIntegerBoardId.getStatusCode());
    }
}