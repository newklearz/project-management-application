package com.newklearz.projectmanagement.repository.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newklearz.projectmanagement.repository.ticketdetails.TicketDetails;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable
{
    private Integer id;

    private String name;

    private String ticketType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private final LocalDateTime dateCreated;

    private String dateUpdated;

    private String status;

    private String resolution;

    private String userRole;

    private TicketDetails ticketDetails;

    public Ticket()
    {
        this.dateCreated = LocalDateTime.now();
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getResolution()
    {
        return resolution;
    }

    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTicketType()
    {
        return ticketType;
    }

    public void setTicketType(String ticketType)
    {
        this.ticketType = ticketType;
    }

    public LocalDateTime getDateCreated()
    {
        return dateCreated;
    }

    public String getDateUpdated()
    {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated)
    {
        this.dateUpdated = dateUpdated;
    }

    public String getUserRole()
    {
        return userRole;
    }

    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public TicketDetails getTicketDetails()
    {
        return ticketDetails;
    }

    public void setTicketDetails(TicketDetails ticketDetails)
    {
        this.ticketDetails = ticketDetails;
    }

    @Override
    public String toString()
    {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", userRole='" + userRole + '\'' +
                ", ticketDetails=" + ticketDetails +
                '}';
    }
}
