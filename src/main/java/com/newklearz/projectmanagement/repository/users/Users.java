package com.newklearz.projectmanagement.repository.users;


import com.newklearz.projectmanagement.repository.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private Integer id;

    private String username;

    private String email;

    private List<Ticket> ticketList = new ArrayList<>();

    public Users()
    {

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Ticket> getTicketList()
    {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList)
    {
        this.ticketList = ticketList;
    }

    @Override
    public String toString()
    {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}
