package com.newklearz.registration;

public class RegistrationRequest
{
    private final String userName;
    private final String email;
    private final String password;

    public RegistrationRequest(String userName, String email, String password)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;

    }

    public String getUserName()
    {
        return userName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

}
