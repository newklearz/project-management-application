package com.newklearz.repository.ticketdetails;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticketdetails")
public class TicketDetails implements Serializable, Cloneable
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

    public TicketDetails()
    {
    }

    public TicketDetails(TicketDetails ticketDetails)
    {
        this.description = ticketDetails.getDescription();
        this.teamName = ticketDetails.getTeamName();
        this.sprintName = ticketDetails.getSprintName();
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
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    @Override
    public String toString()
    {
        return "TicketDetails{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", teamName='" + teamName + '\'' +
            ", sprintName='" + sprintName + '\'' +
            '}';
    }
}
