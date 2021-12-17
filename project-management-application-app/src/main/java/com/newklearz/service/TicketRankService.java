package com.newklearz.service;

import com.newklearz.DTO.BoardDTO;
import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketRankDTO;
import com.newklearz.adapters.BoardAdapter;
import com.newklearz.adapters.TicketAdapter;
import com.newklearz.adapters.TicketRankAdapter;
import com.newklearz.repository.rank.TicketRank;
import com.newklearz.repository.rank.TicketRankRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TicketRankService
{
    private final TicketRankRepository ticketRankRepository;
    private final BoardService boardService;
    private final TicketService ticketService;

    public TicketRankService(TicketRankRepository repository, BoardService boardService, TicketService ticketService)
    {
        this.ticketRankRepository = repository;
        this.boardService = boardService;
        this.ticketService = ticketService;
    }

    public TicketRankDTO addTicketToBoard(TicketRankDTO ticketRankDTO)
    {
        TicketRank ticketRank = TicketRankAdapter.toEntity(ticketRankDTO);
        TicketRank savedTicketRank = ticketRankRepository.save(ticketRank);
        return TicketRankAdapter.toDTO(savedTicketRank);
    }

    public List<TicketDTO> findTicketsForBoard(Integer id)
    {
        BoardDTO board = boardService.findById(id);
        if (board != null)
        {
            return TicketAdapter.toDTOList(ticketRankRepository.findTicketsForBoard(id));
        }
        throw new EntityNotFoundException("Board with id " + id + " not found");
    }

    public List<BoardDTO> findBoardsForTicket(Integer id)
    {
        TicketDTO ticket = ticketService.findById(id);
        if (ticket != null)
        {
            return BoardAdapter.toDTOList(ticketRankRepository.findBoardsForTicket(id));
        }
        throw new EntityNotFoundException("Ticket with id " + id + " not found");
    }

    public void removeTicketFromBoard(Integer ticketId, Integer boardId)
    {
        ticketRankRepository.removeTicketFromBoard(ticketId, boardId);
    }
}