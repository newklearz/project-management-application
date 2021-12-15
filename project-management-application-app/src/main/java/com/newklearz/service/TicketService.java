package com.newklearz.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.newklearz.DTO.TicketDTO;
import com.newklearz.DTO.TicketDetailsDTO;
import com.newklearz.DTO.UsersDTO;
import com.newklearz.adapters.TicketAdapter;
import com.newklearz.adapters.TicketDetailsAdapter;
import com.newklearz.adapters.UserAdapter;
import com.newklearz.repository.ticket.Ticket;
import com.newklearz.repository.ticket.TicketRepository;
import com.newklearz.repository.ticketdetails.TicketDetails;
import com.newklearz.repository.ticketdetails.TicketDetailsRepository;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;
    private final TicketDetailsRepository ticketDetailsRepository;
    private final UserService userService;
    private final BoardService boardService;

    public TicketService(TicketRepository ticketRepository, TicketDetailsRepository ticketDetailsRepository,
        UserService userService, BoardService boardService)
    {
        this.ticketRepository = ticketRepository;
        this.ticketDetailsRepository = ticketDetailsRepository;
        this.userService = userService;
        this.boardService = boardService;
    }

    public List<TicketDTO> findAll()
    {
        return TicketAdapter.toDTOList(ticketRepository.findAll());
    }

    public TicketDTO findById(Integer id)
    {
        Ticket ticket = getTicketById(id);
        return TicketAdapter.toDTO(ticket);
    }

    public TicketDTO createTicket(TicketDTO ticketDTO)
    {
        UsersDTO userFound = userService.findById(ticketDTO.getCreatedBy().getId());
        UsersDTO dummyUser = userService.findById(1);

        Ticket ticket = TicketAdapter.toEntity(ticketDTO);
        ticket.setCreatedBy(UserAdapter.toEntity(userFound));
        ticket.setAssignedTo(UserAdapter.toEntity(dummyUser));

        Ticket savedTicket = ticketRepository.save(ticket);
        return TicketAdapter.toDTO(savedTicket);
    }

    public TicketDTO updateTicket(Integer id, TicketDTO ticketDTO)
    {
        Ticket ticket = getTicketById(id);
        if (!ticket.getId().equals(ticketDTO.getId()))
        {
            throw new RuntimeException("Id of entity not the same with path id");
        }
        return TicketAdapter.toDTO(ticketRepository.save(TicketAdapter.toEntity(ticketDTO)));
    }

    public void deleteTicket(Integer id)
    {
        ticketRepository.deleteById(id);
    }

    private Ticket getTicketById(Integer id)
    {
        Optional<Ticket> optional = ticketRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Ticket with id " + id + " not found"));
    }

    public TicketDetailsDTO updateTicketDetails(Integer id, TicketDetailsDTO ticketDetailsDTO)
    {
        Ticket ticket = getTicketById(id);
        TicketDetails theDetails = ticket.getTicketDetails();
        ticketDetailsRepository.mergeAndFlush(theDetails, ticketDetailsDTO);
        return TicketDetailsAdapter.toDTO(ticket.getTicketDetails());
    }

    public TicketDetailsDTO findTicketDetailsForTicket(Integer id)
    {
        Ticket ticket = getTicketById(id);
        return TicketDetailsAdapter.toDTO(ticket.getTicketDetails());
    }

    public TicketDTO cloneById(Integer id) throws CloneNotSupportedException
    {
        Ticket ticket = getTicketById(id);
        Ticket newTicket = new Ticket((Ticket) ticket.clone());
        return TicketAdapter.toDTO(ticketRepository.save(newTicket));
    }

    public List<TicketDTO> findAllTicketsCreatedByUser(Integer id)
    {
        UsersDTO user = userService.findById(id);
        if (user != null)
        {
            return TicketAdapter.toDTOList(ticketRepository.findTicketsCreatedByUser(id));
        }
        throw new EntityNotFoundException("User with id " + id + " not found");
    }

    public List<TicketDTO> findAllTicketsAssignedToUser(Integer id)
    {
        UsersDTO user = userService.findById(id);
        if (user != null)
        {
            return TicketAdapter.toDTOList(ticketRepository.findTicketsAssignedToUser(id));
        }
        throw new EntityNotFoundException("User with id " + id + " not found");
    }
}