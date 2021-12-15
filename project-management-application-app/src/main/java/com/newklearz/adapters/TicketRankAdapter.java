package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.DTO.TicketRankDTO;
import com.newklearz.repository.rank.TicketRank;

public class TicketRankAdapter
{
    public static TicketRankDTO toDTO(TicketRank ticketRank)
    {
        TicketRankDTO ticketRankDTO = new TicketRankDTO();
        ticketRankDTO.setTicketRankForeignKey(TicketRankForeignKeyAdapter.toDTO(ticketRank.getTicketRankForeignKey()));
        ticketRankDTO.setAssignedBoard(BoardAdapter.toDTO(ticketRank.getAssignedBoard()));
        ticketRankDTO.setAssignedTicket(TicketAdapter.toDTO(ticketRank.getAssignedTicket()));
        ticketRankDTO.setTicketRank(ticketRank.getTicketRank());
        return ticketRankDTO;
    }

    public static TicketRank toEntity(TicketRankDTO ticketRankDTO)
    {
        TicketRank ticketRank = new TicketRank();
        if (ticketRankDTO != null)
        {
            ticketRank
                .setTicketRankForeignKey(TicketRankForeignKeyAdapter.toEntity(ticketRankDTO.getTicketRankForeignKey()));
            ticketRank.setAssignedBoard(BoardAdapter.toEntity(ticketRankDTO.getAssignedBoard()));
            ticketRank.setAssignedTicket(TicketAdapter.toEntity(ticketRankDTO.getAssignedTicket()));
            ticketRank.setTicketRank(ticketRankDTO.getTicketRank());
        }
        else
        {
            return new TicketRank();
        }
        return ticketRank;
    }

    public static List<TicketRankDTO> toDTOList(List<TicketRank> ticketRanks)
    {
        return ticketRanks
            .stream().map(TicketRankAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<TicketRank> toEntityList(List<TicketRankDTO> ticketRankDTOList)
    {
        return ticketRankDTOList
            .stream()
            .map(TicketRankAdapter::toEntity)
            .collect(Collectors.toList());
    }
}