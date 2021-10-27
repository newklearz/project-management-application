package com.newklearz.projectmanagement.repository.ticketdetails;

import com.newklearz.projectmanagement.repository.ticket.Ticket;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticketdetails")
public class TicketDetails implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "sprint_name")
    private String sprintName;

    @OneToOne(mappedBy = "ticketDetails")
    private Ticket ticket;

    public TicketDetails()
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getSprintName()
    {
        return sprintName;
    }

    public void setSprintName(String sprintName)
    {
        this.sprintName = sprintName;
    }

    @Override
    public String toString()
    {
        return "TicketDetails{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", teamName='" + teamName + '\'' +
            ", sprintName='" + sprintName + '\'' +
            ", ticket=" + ticket +
            '}';
    }
}
