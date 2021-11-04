package com.newklearz.repository.ticket;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.newklearz.repository.ticketdetails.TicketDetails;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable, Cloneable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticket_type")
    private String ticketType;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "date_updated")
    private String dateUpdated;

    @Column(name = "status")
    private String status;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "user_role")
    private String userRole;

    @JoinColumn(name = "ticket_details_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private TicketDetails ticketDetails;

    public Ticket()
    {

    }

    public Ticket(String name, String ticketType, String dateCreated, String dateUpdated, String status,
        String resolution, String userRole)
    {
        this.name = name;
        this.ticketType = ticketType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.resolution = resolution;
        this.userRole = userRole;
    }

    public Ticket(Ticket ticket) throws CloneNotSupportedException
    {
        this.name = "cloned " + ticket.getName();
        this.ticketType = ticket.getTicketType();
        this.dateUpdated = ticket.getDateUpdated();
        this.dateCreated = ticket.getDateCreated();
        this.status = ticket.getStatus();
        this.resolution = ticket.getResolution();
        this.userRole = ticket.getUserRole();
        this.ticketDetails = ticket.getTicketDetails();
        this.ticketDetails = new TicketDetails((TicketDetails) ticket.getTicketDetails().clone());
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

    public String getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
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
