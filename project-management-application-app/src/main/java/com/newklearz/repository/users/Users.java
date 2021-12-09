package com.newklearz.repository.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.newklearz.DTO.AppUserRole;

@Entity
@Table(name = "users")
public class Users implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_user_role")
    private AppUserRole appUserRole;

    private boolean isActive = true;

    public Users()
    {

    }

    public Users(String userName, String email, String password, AppUserRole appUserRole)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
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

    public AppUserRole getAppUserRole()
    {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole)
    {
        this.appUserRole = appUserRole;
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

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    @Override
    public String toString()
    {
        return "Users{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", appUserRole=" + appUserRole +
            ", isActive=" + isActive +
            '}';
    }
}
