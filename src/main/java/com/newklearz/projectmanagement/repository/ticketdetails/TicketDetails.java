package com.newklearz.projectmanagement.repository.ticketdetails;

import java.io.Serializable;

public class TicketDetails implements Serializable
{
    private Integer id;

    private String description;

    private String teamName;

    private String sprintName;

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
                '}';
    }
}
