package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.dto.TicketRankForeignKeyDTO;
import com.newklearz.repository.rank.TicketRankForeignKey;

public class TicketRankForeignKeyAdapter
{
    public static TicketRankForeignKeyDTO toDTO(TicketRankForeignKey ticketRankForeignKey)
    {
        TicketRankForeignKeyDTO ticketRankForeignKeyDTO = new TicketRankForeignKeyDTO();
        ticketRankForeignKeyDTO.setTicketId(ticketRankForeignKey.getTicketId());
        ticketRankForeignKeyDTO.setBoardId(ticketRankForeignKey.getBoardId());
        return ticketRankForeignKeyDTO;
    }

    public static TicketRankForeignKey toEntity(TicketRankForeignKeyDTO ticketRankForeignKeyDTO)
    {
        TicketRankForeignKey ticketRankForeignKey = new TicketRankForeignKey();
        if (ticketRankForeignKey != null)
        {
            ticketRankForeignKey.setTicketId(ticketRankForeignKeyDTO.getTicketId());
            ticketRankForeignKey.setBoardId(ticketRankForeignKeyDTO.getBoardId());
        }
        else
        {
            return new TicketRankForeignKey();
        }
        return ticketRankForeignKey;
    }

    public static List<TicketRankForeignKeyDTO> toDTOList(List<TicketRankForeignKey> ticketRankForeignKeys)
    {
        return ticketRankForeignKeys
            .stream().map(TicketRankForeignKeyAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<TicketRankForeignKey> toEntityList(List<TicketRankForeignKeyDTO> ticketRankForeignKeyDTOList)
    {
        return ticketRankForeignKeyDTOList
            .stream()
            .map(TicketRankForeignKeyAdapter::toEntity)
            .collect(Collectors.toList());
    }
}