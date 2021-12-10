package com.newklearz.DTO;

public class UsersDTO
{
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private boolean isActive;
    private AppUserRole appUserRole;

    public UsersDTO()
    {
    }

    public UsersDTO(Integer id, String userName, String email, String password, AppUserRole appUserRole,
        boolean isActive)
    {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.isActive = isActive;
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

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }
}