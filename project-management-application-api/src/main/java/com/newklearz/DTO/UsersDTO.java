package com.newklearz.DTO;

import java.util.ArrayList;
import java.util.List;

public class UsersDTO
{

    private Integer id;

    private String userName;

    private String email;

    private String password;

    private AppUserRole appUserRole;

    private List<TicketDTO> ticketDTOList = new ArrayList<>();

    public UsersDTO()
    {
    }

    public UsersDTO(Integer id, String userName, String email, String password, AppUserRole appUserRole,
        List<TicketDTO> ticketDTOList)
    {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.ticketDTOList = ticketDTOList;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AppUserRole getAppUserRole()
    {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole)
    {
        this.appUserRole = appUserRole;
    }

    public List<TicketDTO> getTicketDTOList()
    {
        return ticketDTOList;
    }

    public void setTicketDTOList(List<TicketDTO> ticketDTOList)
    {
        this.ticketDTOList = ticketDTOList;
    }

}
