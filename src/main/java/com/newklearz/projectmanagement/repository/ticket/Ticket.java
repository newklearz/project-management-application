package com.newklearz.projectmanagement.repository.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newklearz.projectmanagement.repository.ticketdetails.TicketDetails;

import java.time.LocalDateTime;

public class Ticket {

    private Integer id;

    private String name;

    private String ticketType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private final LocalDateTime dateCreated;

    private String dateUpdated;

    private String userRole;

    private TicketDetails ticketDetails;

    public Ticket() {
        this.dateCreated = LocalDateTime.now();
    }

}
