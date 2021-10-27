package com.newklearz.projectmanagement.repository.ticket;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newklearz.projectmanagement.repository.ticketdetails.TicketDetails;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable, Cloneable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private final LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private String dateUpdated;

    @Column(name = "status")
    private String status;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "user_role")
    private String userRole;

    @OneToOne
    @JoinColumn(name = "ticket_details_id", referencedColumnName = "id")
    private TicketDetails ticketDetails;

    public Ticket()
    {
        this.dateCreated = LocalDateTime.now();
    }

    public Ticket(Ticket ticket)
    {
        this();
        this.name = "cloned " + ticket.getName();
        this.ticketType = ticket.getTicketType();
        this.dateUpdated = ticket.getDateUpdated();
        this.status = ticket.getStatus();
        this.resolution = ticket.getResolution();
        this.userRole = ticket.getUserRole();
        this.ticketDetails = ticket.getTicketDetails();
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
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
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
            ", status='" + status + '\'' +
            ", resolution='" + resolution + '\'' +
            ", userRole='" + userRole + '\'' +
            ", ticketDetails=" + ticketDetails +
            '}';
    }
}
