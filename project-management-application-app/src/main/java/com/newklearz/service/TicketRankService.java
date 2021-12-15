package com.newklearz.service;

import com.newklearz.DTO.TicketRankDTO;
import com.newklearz.adapters.TicketRankAdapter;
import com.newklearz.repository.rank.TicketRank;
import com.newklearz.repository.rank.TicketRankRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketRankService
{
    private final TicketRankRepository ticketRankRepository;

    public TicketRankService(TicketRankRepository repository)
    {
        this.ticketRankRepository = repository;
    }

    public TicketRankDTO addTicketToBoard(TicketRankDTO ticketRankDTO)
    {
        TicketRank ticketRank = TicketRankAdapter.toEntity(ticketRankDTO);
        TicketRank savedTicketRank = ticketRankRepository.save(ticketRank);
        return TicketRankAdapter.toDTO(savedTicketRank);
    }
}