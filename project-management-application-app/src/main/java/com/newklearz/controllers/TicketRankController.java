package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.newklearz.DTO.BoardDTO;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketRankDTO;
import com.newklearz.service.TicketRankService;

@RestController
public class TicketRankController implements TicketRankResource
{
    private final TicketRankService ticketRankService;

    public TicketRankController(TicketRankService ticketRankService)
    {
        this.ticketRankService = ticketRankService;
    }

    @Override
    public ResponseEntity<TicketRankDTO> addTicketToBoard(TicketRankDTO ticketRankDTO)
    {
        return ResponseEntity.ok(ticketRankService.addTicketToBoard(ticketRankDTO));
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getTicketsForBoard(Integer id)
    {
        return ResponseEntity.ok(ticketRankService.findTicketsForBoard(id));
    }

    @Override
    public ResponseEntity<List<BoardDTO>> getBoardsForTicket(Integer id)
    {
        return ResponseEntity.ok(ticketRankService.findBoardsForTicket(id));
    }

    @Override
    public ResponseEntity<Object> removeTicketFromBoard(Integer ticketId, Integer boardId)
    {
        ticketRankService.removeTicketFromBoard(ticketId, boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<TicketDTO> updateTicketPriority(Integer id, TicketRankDTO ticketRankDTO)
    {
        return null;
    }
}
