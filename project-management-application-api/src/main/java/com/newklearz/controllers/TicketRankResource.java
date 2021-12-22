package com.newklearz.controllers;

import com.newklearz.dto.BoardDTO;
import com.newklearz.dto.TicketDTO;
import com.newklearz.dto.TicketRankDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api("TicketRank")
public interface TicketRankResource
{
    String TICKET_RANK_COMMON_PREFIX = "/api/v1/board_tickets";

    @ApiOperation("Adds ticket to a board")
    @ResponseBody
    @PostMapping(TICKET_RANK_COMMON_PREFIX + "/{id}")
    ResponseEntity<TicketRankDTO> addTicketToBoard( @PathVariable("id")Integer id, @RequestBody TicketRankDTO ticketRankDTO);

    @ApiOperation("Retrieves all tickets for a board")
    @ResponseBody
    @GetMapping(TICKET_RANK_COMMON_PREFIX + "/{id}/ticket")
    ResponseEntity<List<TicketDTO>> getTicketsForBoard(@PathVariable("id") Integer id);

    @ApiOperation("Retrieves all boards for a ticket")
    @ResponseBody
    @GetMapping(TICKET_RANK_COMMON_PREFIX + "/{id}/board")
    ResponseEntity<List<BoardDTO>> getBoardsForTicket(@PathVariable("id") Integer id);

    @ApiOperation("Removes a ticket from a board")
    @ResponseBody
    @DeleteMapping(TICKET_RANK_COMMON_PREFIX + "/{ticketId}/{boardId}")
    ResponseEntity<Object> removeTicketFromBoard(@PathVariable("ticketId") Integer ticketId, @PathVariable("boardId") Integer boardId);

    @ApiOperation("Updates ticket priority for a board")
    @ResponseBody
    @PutMapping(TICKET_RANK_COMMON_PREFIX + "/{id}")
    ResponseEntity <Object> updateTicketPriority(@PathVariable("id") Integer id, Integer toPosition, Integer fromPosition);
}