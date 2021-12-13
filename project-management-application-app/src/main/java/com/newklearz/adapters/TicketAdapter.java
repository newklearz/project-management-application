package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.repository.ticket.Ticket;

public class TicketAdapter
{
    public static TicketDTO toDTO(Ticket ticket)
    {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setName(ticket.getName());
        ticketDTO.setTicketType(ticket.getTicketType());
        ticketDTO.setDateUpdated(ticket.getDateUpdated());
        ticketDTO.setDateCreated(ticket.getDateCreated());
        ticketDTO.setStatus(ticket.getStatus());
        ticketDTO.setResolution(ticket.getResolution());
        ticketDTO.setUserRole(ticket.getUserRole());
        ticketDTO.setAssignedTo(UserAdapter.toDTO(ticket.getAssignedTo()));
        ticketDTO.setCreatedBy(UserAdapter.toDTO(ticket.getCreatedBy()));
        ticketDTO.setAssignedBoard(BoardAdapter.toDTO(ticket.getAssignedBoard()));
        ticketDTO.setPriority(ticket.getPriority());
        ticketDTO.setTicketDetails(TicketDetailsAdapter.toDTO(ticket.getTicketDetails()));
        return ticketDTO;
    }

    public static Ticket toEntity(TicketDTO ticketDTO)
    {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setName(ticketDTO.getName());
        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setDateCreated(ticketDTO.getDateCreated());
        ticket.setDateUpdated(ticketDTO.getDateUpdated());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setResolution(ticketDTO.getResolution());
        ticket.setUserRole(ticketDTO.getUserRole());
        ticket.setCreatedBy(UserAdapter.toEntity(ticketDTO.getCreatedBy()));
        ticket.setAssignedTo(UserAdapter.toEntity(ticketDTO.getAssignedTo()));
        ticket.setAssignedBoard(BoardAdapter.toEntity(ticketDTO.getAssignedBoard()));
        ticket.setPriority(ticketDTO.getPriority());
        ticket.setTicketDetails(TicketDetailsAdapter.toEntity(ticketDTO.getTicketDetails()));
        return ticket;
    }

    public static List<TicketDTO> toDTOList(List<Ticket> ticketList)
    {
        return ticketList
            .stream()
            .map(TicketAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<Ticket> toEntityList(List<TicketDTO> ticketDTOList)
    {
        return ticketDTOList
            .stream()
            .map(TicketAdapter::toEntity)
            .collect(Collectors.toList());
    }
}
