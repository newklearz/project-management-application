package com.newklearz.projectmanagement.repository.users;

import com.newklearz.projectmanagement.repository.ticket.Ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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
