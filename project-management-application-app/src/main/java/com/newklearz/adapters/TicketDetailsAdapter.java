package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.dto.TicketDetailsDTO;
import com.newklearz.repository.ticketdetails.TicketDetails;

public class TicketDetailsAdapter
{
    public static TicketDetailsDTO toDTO(TicketDetails ticketDetails)
    {
        TicketDetailsDTO ticketDetailsDTO = new TicketDetailsDTO();
        ticketDetailsDTO.setId(ticketDetails.getId());
        ticketDetailsDTO.setDescription(ticketDetails.getDescription());
        ticketDetailsDTO.setTeamName(ticketDetails.getTeamName());
        ticketDetailsDTO.setSprintName(ticketDetails.getSprintName());
        return ticketDetailsDTO;
    }

    public static TicketDetails toEntity(TicketDetailsDTO ticketDetailsDTO)
    {
        TicketDetails ticketDetails = new TicketDetails();
        if (ticketDetailsDTO != null)
        {
            ticketDetails.setId(ticketDetailsDTO.getId());
            ticketDetails.setDescription(ticketDetailsDTO.getDescription());
            ticketDetails.setTeamName(ticketDetailsDTO.getTeamName());
            ticketDetails.setSprintName(ticketDetailsDTO.getSprintName());
        }
        else
        {
            return new TicketDetails();
        }
        return ticketDetails;
    }

    public static List<TicketDetailsDTO> toDTOList(List<TicketDetails> ticketDetails)
    {
        return ticketDetails
            .stream()
            .map(TicketDetailsAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<TicketDetails> ticketDetails(List<TicketDetailsDTO> ticketDetailsDTOList)
    {
        return ticketDetailsDTOList
            .stream()
            .map(TicketDetailsAdapter::toEntity)
            .collect(Collectors.toList());
    }
}
