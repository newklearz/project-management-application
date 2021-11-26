package com.newklearz.DTO;

public class TicketDetailsDTO
{
    private Integer id;
    private String description;
    private String teamName;
    private String sprintName;

    public TicketDetailsDTO()
    {
    }

    public TicketDetailsDTO(Integer id, String description, String teamName, String sprintName)
    {
        this.id = id;
        this.description = description;
        this.teamName = teamName;
        this.sprintName = sprintName;
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
}