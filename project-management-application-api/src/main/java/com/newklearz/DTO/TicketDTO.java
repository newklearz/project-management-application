package com.newklearz.DTO;

public class TicketDTO implements Cloneable
{

    private Integer id;

    private String name;

    private String ticketType;

    private String dateCreated;

    private String dateUpdated;

    private String status;

    private String resolution;

    private String userRole;

    private TicketDetailsDTO ticketDetailsDTO;

    public TicketDTO()
    {

    }

    public TicketDTO(TicketDTO ticket)
    {

        this.name = "cloned " + ticket.getName();
        this.ticketType = ticket.getTicketType();
        this.dateUpdated = ticket.getDateUpdated();
        this.status = ticket.getStatus();
        this.resolution = ticket.getResolution();
        this.userRole = ticket.getUserRole();
        this.ticketDetailsDTO = ticket.getTicketDetailsDTO();
    }

    public TicketDTO(Integer id, String name, String ticketType, String dateCreated, String dateUpdated, String status,
        String resolution, String userRole, TicketDetailsDTO ticketDetailsDTO)
    {
        this.id = id;
        this.name = name;
        this.ticketType = ticketType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.resolution = resolution;
        this.userRole = userRole;
        this.ticketDetailsDTO = ticketDetailsDTO;
    }

    public Integer getId()
    {

        return id;
    }

    public TicketDTO setId(Integer id)
    {
        this.id = id;
        return this;
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

    public String getUserRole()
    {
        return userRole;
    }

    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
    }

    public TicketDetailsDTO getTicketDetailsDTO()
    {
        return ticketDetailsDTO;
    }

    public void setTicketDetailsDTO(TicketDetailsDTO ticketDetailsDTO)
    {
        this.ticketDetailsDTO = ticketDetailsDTO;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

}
