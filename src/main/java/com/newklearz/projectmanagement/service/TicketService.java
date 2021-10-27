package com.newklearz.projectmanagement.service;

import com.newklearz.projectmanagement.repository.ticket.Ticket;
import com.newklearz.projectmanagement.repository.ticket.TicketRepository;
import com.newklearz.projectmanagement.repository.ticketdetails.TicketDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService
{
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository)
    {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAll()
    {
        return ticketRepository.findAll();
    }

    public Ticket findById(Integer id)
    {
        return getTicketById(id);
    }

    public Ticket createTicket(Ticket ticket)
    {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Integer id, Ticket ticket)
    {
        Ticket theTicket = getTicketById(id);

        if (!theTicket.getId().equals(ticket.getId()))
        {
            throw new RuntimeException("Id of entity not the same with path id");
        }
        return ticketRepository.save(theTicket);
    }

    public void deleteTicket(Integer id)
    {
        ticketRepository.deleteById(id);
    }

    private Ticket getTicketById(Integer id)
    {
        Optional<Ticket> optional = ticketRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public TicketDetails addNewDetailsToTicket(Integer ticketId, TicketDetails ticketDetails)
    {
        Ticket ticket = getTicketById(ticketId);
        TicketDetails theDetails = ticket.getTicketDetails();

        if (theDetails != null)
        {
            ticket.setTicketDetails(ticketDetails);
            ticketRepository.save(ticket);

        }
        else
        {
            throw new RuntimeException("The ticket with the id of " + ticketId + " has no details");
        }

        return ticket.getTicketDetails();
    }

    public TicketDetails findTicketDetailsForTicket(Integer ticketId)
    {
        Ticket ticket = getTicketById(ticketId);
        return ticket.getTicketDetails();
    }

    public Ticket cloneById(Integer id) throws CloneNotSupportedException
    {
        Ticket ticket = getTicketById(id);
        Ticket newTicket = new Ticket((Ticket) ticket.clone());
        return ticketRepository.save(newTicket);
    }
}
