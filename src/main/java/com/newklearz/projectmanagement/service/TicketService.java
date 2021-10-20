package com.newklearz.projectmanagement.service;

import com.newklearz.projectmanagement.repository.ticket.TicketRepository;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
