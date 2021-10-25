package com.newklearz.projectmanagement.repository.ticketdetails;

public class TicketDetails
{
    private Integer id;

    private String description;

    private String teamName;

    private String status;

    private String resolution;

    private String sprintName;

    public TicketDetails() {
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
                ", status='" + status + '\'' +
                ", resolution='" + resolution + '\'' +
                ", sprintName='" + sprintName + '\'' +
                '}';
    }
}
